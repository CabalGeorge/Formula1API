# Stage 1: Build the Spring Boot app using Maven
FROM maven:3.9.8-eclipse-temurin-21 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Build the Spring Boot application (produces a jar file in the target folder)
RUN mvn clean install -DskipTests

# Stage 2: Create a runtime image with OpenJDK 21
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file built in the previous stage
COPY --from=builder /app/target/formula1-0.0.1-SNAPSHOT.jar /app/formula1.jar

# Expose the port that the app will run on (default for Spring Boot is 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/formula1.jar"]