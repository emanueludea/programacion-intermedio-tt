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
# Production stage
# =========================================
FROM alpine/java:21-jre

# Create a non-root user and group
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Create app directory and set ownership
WORKDIR /app
RUN chown -R appuser:appgroup /app

# Copy the JAR file from builder stage
COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

# Switch to non-root user
USER appuser

# Expose port
EXPOSE 8080

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application with JVM optimizations
ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-XX:+UseStringDeduplication", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "app.jar"]

# Use the following commands:
# Build: docker build -t api_rest:latest .
# Run: docker run -p 8080:8080 api_rest:latest
# Run with environment variables: docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod api_rest:latest