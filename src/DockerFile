# Use a Java 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file (προσαρμόζεις αν έχει άλλο όνομα)
COPY target/library-management-0.0.1-SNAPSHOT.jar app.jar

# Run the app
CMD ["java", "-jar", "app.jar"]
