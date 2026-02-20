# Product API - Spring Boot REST Application

## Project Overview

This is a RESTful Product Management API built using Java and Spring Boot.

The application supports:

- Full CRUD operations for Products
- JWT-based Authentication
- Role-based Authorization
- MySQL Database integration
- Swagger API documentation
- Docker & Docker Compose setup
- Unit Testing with JUnit & Mockito

This project was developed as a backend assignment for interview selection.

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- MySQL
- Spring Security
- JWT Authentication
- Swagger (Springdoc OpenAPI)
- JUnit 5 & Mockito
- Docker & Docker Compose
- Maven

---

## Project Structure

src/main/java/com/lucky/productapi

- config          → Security & configuration classes  
- controller      → REST Controllers  
- service         → Business logic  
- repository      → JPA Repositories  
- entity          → JPA Entities  
- dto             → Request DTOs  
- security        → JWT & Security classes  
- exception       → Global Exception Handling  

---

## Authentication Flow (JWT)

1. User logs in using `/api/v1/auth/login`
2. If credentials are valid:
   - JWT token is generated
3. Token must be sent in request header:

Authorization: Bearer your_jwt_token

4. Protected endpoints require valid token

---

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | /api/v1/auth/login | Login & get JWT |
| POST | /api/v1/auth/register | Register new user |

---

### Products

| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /api/v1/products | Get all products (Pagination supported) |
| GET | /api/v1/products/{id} | Get product by ID |
| POST | /api/v1/products | Create product |
| PUT | /api/v1/products/{id} | Update product |
| DELETE | /api/v1/products/{id} | Delete product |

---

## Database Schema

### Product Table

- id (Primary Key)
- product_name
- created_by
- created_on
- modified_by
- modified_on

### Item Table

- id (Primary Key)
- product_id (Foreign Key)
- quantity

---

## Testing

Unit tests implemented using:

- JUnit 5
- Mockito

To run tests:

mvn test

---

## Running the Application

### Run Locally

mvn clean install  
mvn spring-boot:run  

Application runs on:

http://localhost:8081

Swagger UI:

http://localhost:8081/swagger-ui/index.html

---

### Run Using Docker

Build and start containers:

docker compose up --build

This will start:

- MySQL container
- Spring Boot application container

---

## Pagination Support

Example:

GET /api/v1/products?page=0&size=5

---

## Security Implementation

- Stateless Authentication
- JWT-based access control
- Custom JWT filter
- Role-based authorization
- Password encryption using BCrypt

---

## Design Decisions

- Simple layered architecture (Controller → Service → Repository)
- Basic JWT implementation suitable for beginner-level understanding
- Audit fields (createdBy, createdOn) handled during product creation
- Global exception handling for consistent error responses
- Dockerized for easy deployment

---

## Assumptions

- This project focuses on backend REST API implementation.
- Architecture kept simple and beginner-friendly.
- Advanced enterprise-level optimizations intentionally avoided.

---

## Author

Laxman Kale 
Java Backend Developer (Fresher)
