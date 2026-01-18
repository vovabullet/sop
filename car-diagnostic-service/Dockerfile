# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# First, build and install contract dependencies
COPY events-contract/pom.xml events-contract/pom.xml
COPY events-contract/src events-contract/src
RUN cd events-contract && mvn clean install -DskipTests -B

COPY car-api-contract/pom.xml car-api-contract/pom.xml
COPY car-api-contract/src car-api-contract/src
RUN cd car-api-contract && mvn clean install -DskipTests -B

# Copy car-diagnostic-service pom.xml and download dependencies
COPY car-diagnostic-service/pom.xml car-diagnostic-service/pom.xml
WORKDIR /build/car-diagnostic-service
RUN mvn dependency:go-offline -B || true

# Copy car-diagnostic-service source and build
COPY car-diagnostic-service/src ./src
RUN mvn clean package -DskipTests -B

# Runtime stage
# Используем легковесный образ JRE 17 на базе Alpine Linux
FROM eclipse-temurin:17-jre-alpine

# Создаем рабочую директорию
WORKDIR /app

# Создаем пользователя 'javauser', чтобы не запускать приложение от root (Security Best Practice)
RUN addgroup -S javauser && adduser -S javauser -G javauser

# Copy JAR from build stage
COPY --from=build /build/car-diagnostic-service/target/*.jar app.jar

# Меняем владельца файла
RUN chown javauser:javauser /app/app.jar

# Переключаемся на пользователя
USER javauser

# Документируем порт (этот порт должен совпадать с server.port в properties)
EXPOSE 8080

# Запускаем приложение
# Использование exec формы ["..."] позволяет корректно передавать сигналы остановки (SIGTERM)
ENTRYPOINT ["java", "-jar", "app.jar"]