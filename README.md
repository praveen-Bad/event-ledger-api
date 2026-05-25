# event-ledger-api# Setup Instructions

## Prerequisites

Make sure the following are installed on your system:

- Java 21 or higher
- Maven 3.9 or higher
- Git

Verify installation:

```bash
java -version
mvn -version
git --version
```

---

# Clone Repository

```bash
git clone https://github.com/praveen-Bad/event-ledger-api.git
```

---

# Navigate to Project Directory

```bash
cd event-ledger-api
```

---

# Install Dependencies

Build the project and download all required dependencies:

```bash
mvn clean install
```

---

# How to Start the Application

Run the Spring Boot application using:

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

# Swagger API Documentation

Access Swagger UI at:

```text
http://localhost:8080/swagger-ui.html
```

---

# H2 Database Console

Access H2 Console at:

```text
http://localhost:8080/h2-console
```

Use the following configuration:

```text
JDBC URL: jdbc:h2:mem:eventdb
Username: sa
Password:
```

---

# How to Run the Tests

Run all automated tests using:

```bash
mvn test
```

This executes:
- Idempotency tests
- Validation tests
- Balance computation tests
- Event ordering tests
