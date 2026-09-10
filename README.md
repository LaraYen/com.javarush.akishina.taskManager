Приложение Task Manager

На данный момент реализована только работа с задачами.

Запуск приложения:

### 1. Соберите JAR приложения локально
```bash
mvn clean package
```
Убедитесь, что в каталоге `target/` появился файл:

```text
taskManager-0.0.1-SNAPSHOT.jar
```

или аналогичный.

### 2. Запустите все сервисы одной командой

```bash
docker-compose up -d
```

Ручки и примеры запросов:

    GET http://localhost:8080/api/task
    GET http://localhost:8080/api/task/{id}

    POST http://localhost:8080/api/task

    {
        "title": "My second task",
        "description": "lalalalal some description",
        "deadline": "2026-09-11T00:00:00Z"
    }

    PATCH http://localhost:8080/api/task/{id}

    {
        "title": "Changed title",
        "description": "Changed description",
        "taskStatus": "IN_PROGRESS",
        "deadline": "2026-09-11T00:00:00Z"
    }

    DELETE http://localhost:8080/api/task/{id}
