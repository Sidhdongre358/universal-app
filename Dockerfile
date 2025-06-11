# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file (replace with your actual JAR name)
COPY target/universal-0.0.1-SNAPSHOT.jar app.jar

# Expose port (adjust as needed)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
