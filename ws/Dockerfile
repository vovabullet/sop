# Этап сборки (чтобы не требовать установленного Java/Maven на хосте)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Copy ws pom.xml and download dependencies
COPY ws/pom.xml ws/pom.xml
WORKDIR /build/ws
RUN mvn dependency:go-offline -B || true

# Copy ws source and build
COPY ws/src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /build/ws/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]