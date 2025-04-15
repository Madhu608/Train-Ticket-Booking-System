# Use official OpenJDK image as base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the compiled .jar file from the host to the container
COPY target/Indian-Railway-0.0.1-SNAPSHOT.jar app.jar

# Expose port (default Spring Boot port)
EXPOSE 8080

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
