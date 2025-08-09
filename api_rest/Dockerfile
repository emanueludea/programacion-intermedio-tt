FROM maven:3.9.11-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY . .
RUN /app/mvnw clean package

FROM amazoncorretto:21

COPY --from=builder /app/target*jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# Use the following command to build the Docker image:
# docker build -t api_rest:latest .
# Use the following command to run the Docker container:
# docker run -p 8080:8080 api_rest:latest