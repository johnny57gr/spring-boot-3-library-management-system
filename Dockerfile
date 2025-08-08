# Step 1: Build stage
FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app
COPY . .

# ✅ Δώσε εκτελέσιμα δικαιώματα στο mvnw
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# Step 2: Run stage
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=builder /app/target/librarymanagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
