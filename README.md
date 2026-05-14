# Student Management System

A Spring Boot-based backend application for managing students, courses, enrollments, and academic records.

## Overview

The Student Management System provides a RESTful API to manage the lifecycle of students and courses. It supports student registration, course management, enrolling students into courses, and updating student grades and scores.

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4.0.6
- **Build Tool:** Maven
- **Database:** MySQL
- **Libraries:**
    - Spring Data JPA (ORM)
    - Hibernate
    - Lombok (Boilerplate reduction)
    - Jakarta Validation (Data validation)
    - Spotless (Code formatting)

## Requirements

- **JDK:** 21 or higher
- **Maven:** 3.9+ (or use the included `mvnw`)
- **Database:** MySQL 8.0+
- **Lombok Plugin:** Required for IDEs to process annotations

## Setup & Installation

### 1. Database Configuration

1. Create a MySQL database named `student_manager` (or any name of your choice).
2. Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`.
3. Update the following properties with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_manager
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 2. Build the Project

Run the following command to build the project and install dependencies:

```bash
./mvnw clean install
```

### 3. Run the Application

Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

The server will start at `http://localhost:8080` (default port).

## API Endpoints

### Students
- `POST /api/v1/students` - Register a new student.
- `GET /api/v1/students/{studentId}` - Get student details by ID.
- `PATCH /api/v1/students/{studentId}` - Update student details.
- `GET /api/v1/students/allStudents?page=0&size=10` - Get paginated list of students (Filterable by `level` and `department`).

### Courses
- `POST /api/v1/courses` - Register a new course.
- `GET /api/v1/courses/{id}` - Get course details by ID.
- `GET /api/v1/courses` - Get all courses.
- `PATCH /api/v1/courses/{id}` - Update course details.

### Enrollments
- `POST /api/v1/enrollments/{studentId}/{courseId}` - Enroll a student in a course.

### Records (Grades/Scores)
- `PATCH /api/v1/records/{studentId}/{courseId}` - Update scores/grades for a student in a specific course.

## Scripts & Maven Goals

- `./mvnw spotless:apply` - Formats the codebase according to the project's style guide (AOSP style).
- `./mvnw spotless:check` - Verifies if the code is correctly formatted.
- `./mvnw test` - Runs unit and integration tests.

## Environment Variables / Configuration

Configuration is managed via `src/main/resources/application.properties`. Key configurations include:

| Property                        | Description            | Default                                       |
|---------------------------------|------------------------|-----------------------------------------------|
| `spring.datasource.url`         | JDBC URL for MySQL     | `jdbc:mysql://localhost:3306/student_manager` |
| `spring.datasource.username`    | Database username      | `root`                                        |
| `spring.datasource.password`    | Database password      | `{DB_PASSWORD}`                               |
| `spring.jpa.hibernate.ddl-auto` | Hibernate DDL strategy | `update`                                      |

## Project Structure

```text
student-manager/
├── src/main/java/com/project/student_manager/
│   ├── controller/      # REST API Controllers
│   ├── dto/             # Data Transfer Objects
│   ├── entities/        # JPA Entities
│   ├── enums/           # Enumerations (Level, Department, etc.)
│   ├── exceptions/      # Custom Exception Handlers
│   ├── repositories/    # Spring Data JPA Repositories
│   ├── request/         # API Request Payloads
│   ├── response/        # API Response Envelopes
│   └── service/         # Business Logic Layer
├── src/main/resources/
│   ├── application.properties
│   └── application.properties.example
└── src/test/            # Unit and Integration Tests
```

## Testing

To run tests, execute:
```bash
./mvnw test
```
The project uses `spring-boot-starter-test` for testing the web layer and JPA repositories.

## License

TODO: Add license information.
