# SOP — платформа диагностики автомобилей

Многомодульный проект на Java 17 и Spring Boot с микросервисной архитектурой.  
Основной сценарий: управление автомобилями и механиками, запуск диагностики через gRPC и публикация событий через RabbitMQ.

## Состав проекта

- `car-diagnostic-service` — основной REST/GraphQL API (порт `8080`)
- `diagnostic-service` — gRPC-сервис диагностики (в Docker: `9090`, наружу: `9091`)
- `audit-service` — аудит событий из RabbitMQ (порт `8082`)
- `ws` (`notification-service`) — WebSocket/уведомления (порт `8083`)
- `car-api-contract` — общий контракт API/модели
- `events-contract` — контракты событий

## Технологии

- Java 17
- Maven (multi-module)
- Spring Boot 3.3.x
- RabbitMQ
- gRPC
- GraphQL
- Prometheus + Zipkin

## Быстрый старт (Docker Compose)

Из корня репозитория:

```bash
docker compose up --build
```

После запуска будут доступны:

- API: `http://localhost:8080`
- RabbitMQ UI: `http://localhost:15672` (`guest/guest`)
- Zipkin: `http://localhost:9411`
- Prometheus: `http://localhost:9090`

## Локальный запуск без Docker

1. Собрать проект:

```bash
mvn clean install
```

2. Запустить инфраструктуру (минимум RabbitMQ, при необходимости Zipkin/Prometheus).
3. Запускать сервисы по модулям (`spring-boot:run`) в нужном порядке:
    1. `diagnostic-service`
    2. `car-diagnostic-service`
    3. `audit-service`
    4. `ws`

## Проверка API

В репозитории есть файл `api.http` с готовыми запросами:

- получение/создание автомобилей
- получение/создание механиков
- запуск диагностики
- GraphQL-запрос списка автомобилей
