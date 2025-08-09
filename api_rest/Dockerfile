# syntax=docker/dockerfile:1

# =========================================
# Stage 1: Dependencies Cache Layer
# =========================================
FROM alpine/java:21-jdk AS deps

# Install required packages
RUN apk add --no-cache tar

WORKDIR /app

# Copy Maven wrapper and make it executable
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Copy only dependency-related files for better caching
COPY pom.xml .

# Download dependencies (cached layer)
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -B

# =========================================
# Stage 2: Build Application
# =========================================
FROM alpine/java:21-jdk AS builder

# Install required packages
RUN apk add --no-cache tar

WORKDIR /app

# Copy Maven wrapper and dependencies from previous stage
COPY --from=deps /app/.mvn .mvn
COPY --from=deps /app/mvnw .
COPY --from=deps /app/pom.xml .

# Copy source code
COPY src ./src

# Build the application with Maven cache
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw clean package -DskipTests -B

# =========================================
# Stage 3: Extract JAR layers for better caching
# =========================================
FROM alpine/java:21-jdk AS extractor

WORKDIR /app

# Copy the built JAR
COPY --from=builder /app/target/*.jar app.jar

# Extract JAR layers for better Docker layer caching
RUN java -Djarmode=layertools -jar app.jar extract

# =========================================
# Stage 4: Distroless production stage
# =========================================
FROM gcr.io/distroless/java21-debian12:nonroot AS runner

WORKDIR /app

# Copy JAR layers in order of change frequency (least to most)
# This provides better caching when only application code changes
COPY --from=extractor --chown=nonroot:nonroot /app/dependencies/ ./
COPY --from=extractor --chown=nonroot:nonroot /app/spring-boot-loader/ ./
COPY --from=extractor --chown=nonroot:nonroot /app/snapshot-dependencies/ ./
COPY --from=extractor --chown=nonroot:nonroot /app/application/ ./

EXPOSE 8080

# Add labels for better container management
LABEL org.opencontainers.image.title="API REST Bootcamp" \
      org.opencontainers.image.description="Spring Boot REST API" \
      org.opencontainers.image.version="1.0.0" \
      org.opencontainers.image.source="https://github.com/yourorg/bootcamp-programacion-intermedio"

# Optimized JVM settings for containers
ENTRYPOINT ["java", \
    "-server", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-XX:+UseStringDeduplication", \
    "-XX:+OptimizeStringConcat", \
    "-XX:+UseCompressedOops", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Dspring.backgroundpreinitializer.ignore=true", \
    "org.springframework.boot.loader.launch.JarLauncher"]