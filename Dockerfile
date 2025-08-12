# --- Build stage ---
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/librarymanagement-0.0.1-SNAPSHOT.jar app.jar

# Προαιρετικά, λίγη ρύθμιση JVM
ENV JAVA_TOOL_OPTIONS="-XX:+UseG1GC -XX:MaxRAMPercentage=75"

EXPOSE 8080
# Πολύ σημαντικό: άκου στη θύρα που δίνει το Render
CMD ["sh","-c","java -jar app.jar --server.port=${PORT}"]
