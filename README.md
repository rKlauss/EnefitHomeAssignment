# Enefit home assignment

---

## Features

- **User Authentication**: Secure login for customers.
- **Consumption Tracking**: Retrieve monthly energy consumption data.
- **Market Price Integration**: Fetch and use real-time market prices for energy cost calculations.
- **Data Visualization Ready**: API responses optimized for frontend consumption and visualization.

---

## Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL** (for production)
- **Lombok** for reducing boilerplate code
- **Docker** for containerization

---

## Getting Started

### Prerequisites

- Java 21
- Gradle
- PostgreSQL 
- Docker

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rKlauss/EnefitHomeAssignment.git
   cd EnefitHomeAssignment/backend
    ```
    ```bash
   git clone https://github.com/rKlauss/frontend.git
   cd frontend/enefit-frontend
   ```

---

## Docker Setup

1. Navigate to frontend folder and run:
```bash
docker build -t enefitfrontend .
```

2. **Navigate to backend folder and Run with Docker Compose:**

```bash
docker-compose up --build
```
---
## Testing

1. Go to http://localhost:5173

2. Use dummy data to login:

| Username     | Password  |
| ------------ | --------- |
| john_doe     | password123|
| alice_brown  | pass789 |
| jane_smith   | securePass456 |

Customer john_doe has more than 1 metering point, others have only 1.

## Author
Rasmus Klaus
