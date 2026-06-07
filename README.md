# j-court-service

A Spring Boot microservice for managing court cases — administrative articles, offenders, violations, and court decisions. When a decision is created, the service asks Google Gemini for a recommendation (fine amount, decision type, judge and comment), persists the decision, and publishes a `decision.created` event to Kafka for downstream consumers (e.g. notifications).

## Features

- **Articles** — CRUD for legal/administrative articles (code, title, description, fine, decision type).
- **Offenders** — manage offenders and look them up by personal identification number; enriched via an external GCP user service.
- **Violations** — register violations that link an offender to an article.
- **Court decisions** — create decisions from violations, with fine/type/comment **recommended by Google Gemini AI**.
- **Event publishing** — emits a `decision.created` Kafka event when a decision is created.
- **Database migrations** — schema managed by Flyway over PostgreSQL.

## Tech Stack

| Concern         | Technology                              |
|-----------------|-----------------------------------------|
| Language        | Java 17                                 |
| Framework       | Spring Boot 4.0.1 (Web MVC, Data JPA, Security, Validation) |
| Build           | Gradle (wrapper included)               |
| Database        | PostgreSQL                              |
| Migrations      | Flyway                                  |
| Messaging       | Apache Kafka (Spring Kafka)             |
| Mapping         | MapStruct                               |
| Boilerplate     | Lombok                                  |
| HTTP client     | Spring `RestClient`                     |
| AI              | Google Gemini API                       |

## Architecture

```
Controller  ->  Service  ->  Repository (JPA)  ->  PostgreSQL
                   |
                   +--> GeminiAdapter   (AI decision recommendation)
                   +--> GcpAdapter      (external user lookup)
                   +--> DecisionCreatedProducer --> Kafka topic "decision.created"
```

Core domain entities:

- `ArticleEntity` — a legal article (unique `code`, `title`, `description`, `fine`, `decisionType`).
- `OffenderEntity` — a person (name, surname, address, citizenship, personal ID number, age) linked to an article.
- `ViolationEntity` — links an offender and an article, with a description, status, and offense time.
- `CourtDecisionEntity` — a decision for a violation (decision number, type, fine amount, comment, judge, status).

## API Endpoints

Base path: `/api/court`

### Articles — `/api/court/articles`
| Method | Path     | Description                  |
|--------|----------|------------------------------|
| POST   | `/`      | Create an article            |
| GET    | `/`      | List articles (paginated)    |
| GET    | `/{id}`  | Get an article by id         |
| PUT    | `/{id}`  | Update an article            |
| DELETE | `/{id}`  | Delete an article            |

### Offenders — `/api/court/offender`
| Method | Path                                  | Description                          |
|--------|---------------------------------------|--------------------------------------|
| GET    | `/`                                   | List offenders (paginated)           |
| GET    | `/personal-number/{personalNumber}`   | Get an offender by personal ID number|
| PUT    | `/{id}`                               | Update an offender                   |

### Violations — `/api/court/violations`
| Method | Path     | Description                   |
|--------|----------|-------------------------------|
| POST   | `/`      | Create a violation            |
| GET    | `/`      | List violations (paginated)   |
| GET    | `/{id}`  | Get a violation by id         |
| PUT    | `/{id}`  | Update a violation            |

### Court Decisions — `/api/court/decision`
| Method | Path                | Description                                         |
|--------|---------------------|-----------------------------------------------------|
| POST   | `/`                 | Create a decision (AI recommendation + Kafka event) |
| GET    | `/{id}`             | Get a decision by id                                |
| PUT    | `/{id}`             | Update a decision                                   |
| PATCH  | `/status/{id}`      | Update a decision's status (`?decisionStatus=...`)  |

> Note: Spring Security is configured to permit all requests (`permitAll`), so endpoints are currently open.

## Configuration

The service is configured via environment variables (see `src/main/resources/application.yml`).

### Required

| Variable                        | Description                                  |
|---------------------------------|----------------------------------------------|
| `DB_URL`                        | PostgreSQL JDBC URL                          |
| `DB_USERNAME`                   | Database username                           |
| `DB_PASSWORD`                   | Database password                           |
| `GEMINI_URL`                    | Google Gemini API endpoint URL              |
| `GEMINI_KEY`                    | Google Gemini API key                       |
| `NOTIFICATION_MAIL_URL`         | Notification service mail URL               |
| `NOTIFICATION_MERCHANT_LOGIN`   | Notification service merchant login         |
| `NOTIFICATION_MERCHANT_PASSWORD`| Notification service merchant password      |

### Optional

| Variable        | Default                                                  | Description               |
|-----------------|----------------------------------------------------------|---------------------------|
| `GET_USER_URL`  | `http://localhost:8080/gcp_api/api/gcp/users/by-pi`      | External GCP user lookup  |

### Defaults

- **Server port:** `8088`
- **Kafka bootstrap servers:** `localhost:9092`
- **Kafka topic:** `decision.created`

## Getting Started

### Prerequisites

- JDK 17
- PostgreSQL
- Apache Kafka
- A Google Gemini API key

### Run locally

```bash
# Set the required environment variables first (see Configuration), e.g.
export DB_URL=jdbc:postgresql://localhost:5432/court
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
export GEMINI_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent
export GEMINI_KEY=your-api-key

# Run the application
./gradlew bootRun
```

On Windows (PowerShell):

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/court"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "postgres"
$env:GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent"
$env:GEMINI_KEY = "your-api-key"

./gradlew.bat bootRun
```

### Build

```bash
./gradlew clean build
```

### Test

```bash
./gradlew test
```

## Docker

```bash
# Build the jar first
./gradlew clean build

# Build the image
docker build -t j-court-service .

# Run (pass the required env vars)
docker run -p 8088:8088 \
  -e DB_URL=... -e DB_USERNAME=... -e DB_PASSWORD=... \
  -e GEMINI_URL=... -e GEMINI_KEY=... \
  j-court-service
```

> The `Dockerfile` copies `target/*.jar`. Adjust the build output path (Gradle builds to `build/libs/`) or the `COPY` line as needed.

## Project Structure

```
src/main/java/uzumtech/court/jcourtservice/
├── component/
│   ├── adapter/        # GeminiAdapter, GcpAdapter (external calls)
│   └── kafka/producer/ # DecisionCreatedProducer
├── config/             # Application, Kafka, RestClient, Security config
├── constant/           # Kafka constants and enums
├── controller/         # REST controllers
├── dto/                # request / response / event / error DTOs
├── entity/             # JPA entities
├── exception/          # custom exceptions
├── handler/            # global & REST-client exception handlers
├── mapper/             # MapStruct mappers
├── repository/         # Spring Data JPA repositories
├── service/            # service interfaces + impl
└── utils/              # helpers (prompt building, decision number, JSON parsing)
```
