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
# Distroless production stage
# =========================================
FROM gcr.io/distroless/java21-debian12:nonroot

WORKDIR /app

# Copy JAR file (distroless already has nonroot user)
COPY --from=builder --chown=nonroot:nonroot /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-jar", "app.jar"]

# Use the following commands:
# Build: docker build -t api_rest:latest .
# Run: docker run -p 8080:8080 api_rest:latest
# Run with environment variables: docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod api_rest:latest