# Stage 1: Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only necessary files to leverage Docker cache
COPY pom.xml .
COPY src ./src
#testing
RUN mvn clean test -Dspring.profiles.active=test
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage (Using a lightweight JDK image)
FROM eclipse-temurin:17-jre-alpine

# Set a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/customer-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080



# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
