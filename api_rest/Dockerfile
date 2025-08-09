# syntax=docker/dockerfile:1

# =========================================
# Stage 1: Maven Dependencies Cache
# =========================================
FROM alpine/java:21-jdk AS deps

RUN apk add --no-cache tar

WORKDIR /app

# Copy Maven configuration
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Cache dependencies
RUN --mount=type=cache,target=/root/.m2,sharing=locked \
    ./mvnw dependency:resolve dependency:resolve-sources -B

# =========================================
# Stage 2: Build with cached dependencies
# =========================================
FROM alpine/java:21-jdk AS builder

RUN apk add --no-cache tar

WORKDIR /app

# Copy dependencies and configuration from cache layer
COPY --from=deps /app/.mvn .mvn
COPY --from=deps /app/mvnw .
COPY --from=deps /app/pom.xml .

# Copy source code
COPY src ./src

# Build application with cached dependencies
RUN --mount=type=cache,target=/root/.m2,sharing=locked \
    ./mvnw clean package -DskipTests -B -o

# =========================================
# Stage 3: Production with JRE optimizations
# =========================================
FROM gcr.io/distroless/java21-debian12:nonroot

WORKDIR /app

# Copy JAR with optimal ownership
COPY --from=builder --chown=65532:65532 /app/target/*.jar app.jar

EXPOSE 8080

# Health check endpoint
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD ["java", "-cp", "app.jar", "-Dloader.main=org.springframework.boot.actuator.health.HealthEndpoint", "org.springframework.boot.loader.launch.PropertiesLauncher"]

# Production-optimized JVM settings
ENTRYPOINT ["java", \
    "-server", \
    "-XX:+UnlockExperimentalVMOptions", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-XX:G1HeapRegionSize=16m", \
    "-XX:+UseStringDeduplication", \
    "-XX:+OptimizeStringConcat", \
    "-XX:+UseCompressedClassPointers", \
    "-XX:+UseCompressedOops", \
    "-XX:+TieredCompilation", \
    "-XX:TieredStopAtLevel=1", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Dspring.backgroundpreinitializer.ignore=true", \
    "-Dspring.output.ansi.enabled=NEVER", \
    "-jar", "app.jar"]