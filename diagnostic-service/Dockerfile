# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy diagnostic-service pom.xml and download dependencies
COPY diagnostic-service/pom.xml diagnostic-service/pom.xml
WORKDIR /build/diagnostic-service
RUN mvn dependency:go-offline -B || true

# Copy diagnostic-service source and build
COPY diagnostic-service/src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN addgroup -S javauser && adduser -S javauser -G javauser

# Copy JAR from build stage
COPY --from=build /build/diagnostic-service/target/*.jar app.jar

RUN chown javauser:javauser /app/app.jar
USER javauser

# Порт gRPC
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]