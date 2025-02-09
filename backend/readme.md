# Enefit home assignment

---

## Features

- **User Authentication**: Secure login for customers.
- **Consumption Tracking**: Retrieve monthly energy consumption data.
- **Market Price Integration**: Fetch and use real-time market prices for energy cost calculations.
- **Data Visualization Ready**: API responses optimized for frontend consumption and visualization.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL** (for production)
- **Lombok** for reducing boilerplate code
- **Docker** for containerization

---

## Getting Started

### Prerequisites

- Java 17+
- Gradle
- PostgreSQL (for production)
- Docker (for containerized deployment)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/energy-consumption-backend.git
   cd energy-consumption-backend
   ```
2. **Configure application properties:**
   - Update `src/main/resources/application.properties` or `application.yml` with your database credentials.

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```
---

## Docker Setup

1. **Run with Docker Compose:**

```bash
docker-compose up --build
```

2. **Check Container Health:**

```bash
docker ps
```

---

## Author
Rasmus Klaus