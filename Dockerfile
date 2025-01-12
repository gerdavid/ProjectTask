# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the target directory (generated by Maven or Gradle)
COPY target/projectTask-name.jar app.jar

# Make the container's port 8080 available
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

ENTRYPOINT ["java", "-jar", "/app.jar"]
