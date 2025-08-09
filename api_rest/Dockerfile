FROM alpine/java:21-jdk AS builder

# Install required packages
RUN apk add --no-cache tar

WORKDIR /app

# Copy Maven wrapper and make it executable
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# =========================================
# Production stage with numeric user
# =========================================
FROM alpine/java:21-jre

# Create non-root user with specific UID/GID
RUN addgroup -g 1001 appgroup && \
    adduser -u 1001 -G appgroup -s /bin/sh -D appuser

# Create necessary directories
RUN mkdir -p /app && \
    chown -R 1001:1001 /app

WORKDIR /app

# Copy JAR with proper ownership
COPY --from=builder --chown=1001:1001 /app/target/*.jar app.jar

# Switch to non-root user using numeric ID
USER 1001:1001

EXPOSE 8080

# Optimized JVM settings for containers
ENTRYPOINT ["java", \
    "-server", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-XX:+UnlockExperimentalVMOptions", \
    "-XX:+UseCGroupMemoryLimitForHeap", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "app.jar"]

# Use the following commands:
# Build: docker build -t api_rest:latest .
# Run: docker run -p 8080:8080 api_rest:latest
# Run with environment variables: docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod api_rest:latest