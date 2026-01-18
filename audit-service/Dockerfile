# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# First, build and install events-contract dependency
COPY events-contract/pom.xml events-contract/pom.xml
COPY events-contract/src events-contract/src
RUN cd events-contract && mvn clean install -DskipTests -B

# Copy audit-service pom.xml and download dependencies
COPY audit-service/pom.xml audit-service/pom.xml
WORKDIR /build/audit-service
RUN mvn dependency:go-offline -B || true

# Copy audit-service source and build
COPY audit-service/src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN addgroup -S javauser && adduser -S javauser -G javauser

# Copy JAR from build stage
COPY --from=build /build/audit-service/target/*.jar app.jar

RUN chown javauser:javauser /app/app.jar
USER javauser

# Порт сервиса
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]