# Universal Task Management API

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://img.shields.io/github/actions/workflow/status/Sidhdongre358/universal-task-management/build.yml?branch=main)](https://github.com/Sidhdongre358/universal-task-management/actions)

A robust, production-ready RESTful API for task management with JWT authentication, built with Spring Boot 3.x and Spring Security 6.x.

## 🚀 Features

- **JWT Authentication & Authorization**
  - Secure user registration and login
  - Role-based access control (Admin/User roles)
  - Refresh token mechanism
  - Password encryption

- **Task Management**
  - CRUD operations for tasks
  - Task assignment to users
  - Task status tracking
  - Filtering and pagination

- **API Documentation**
  - Interactive API documentation with Swagger UI
  - Detailed endpoint descriptions
  - Request/response examples

- **Best Practices**
  - Clean Architecture
  - DTO pattern for data transfer
  - Global exception handling
  - Input validation
  - Comprehensive logging

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot 3.1.0
- **Security**: Spring Security 6, JWT
- **Database**: H2 (for development), PostgreSQL (production-ready)
- **Build Tool**: Maven
- **API Documentation**: SpringDoc OpenAPI 2.0
- **Testing**: JUnit 5, Mockito
- **Containerization**: Docker

## 📦 Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Docker (optional, for containerization)
- PostgreSQL (for production)

## 🚀 Getting Started

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sidhdongre358/universal-task-management.git
   cd universal-task-management
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080/api`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:universaldb`)

## 🐳 Docker Deployment

### Prerequisites
- Docker installed on your system
- Maven (for building the JAR file)

### Build and Run with Docker

1. **Build the application JAR**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Build the Docker image**
   ```bash
   docker build -t universal-task-management .
   ```

3. **Run the container**
   ```bash
   docker run -d -p 8080:8080 --name task-management-app universal-task-management
   ```

### Docker Compose (Recommended)

For a production-like environment with database:

1. Create a `docker-compose.yml` file:
   ```yaml
   version: '3.8'
   
   services:
     app:
       build: .
       ports:
         - "8080:8080"
       environment:
         - SPRING_PROFILES_ACTIVE=prod
         - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/universal
         - SPRING_DATASOURCE_USERNAME=postgres
         - SPRING_DATASOURCE_PASSWORD=postgres
       depends_on:
         - db
       restart: unless-stopped
     
     db:
       image: postgres:15-alpine
       environment:
         - POSTGRES_DB=universal
         - POSTGRES_USER=postgres
         - POSTGRES_PASSWORD=postgres
       volumes:
         - postgres_data:/var/lib/postgresql/data
       ports:
         - "5432:5432"
       restart: unless-stopped
   
   volumes:
     postgres_data:
   ```

2. Start the services:
   ```bash
   docker-compose up -d
   ```

### Environment Variables

Configure the application using these environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `dev` |
| `SERVER_PORT` | Application port | `8080` |
| `SPRING_DATASOURCE_URL` | Database URL | `jdbc:h2:mem:universaldb` |
| `SPRING_DATASOURCE_USERNAME` | Database username | `sa` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `` |
| `JWT_SECRET` | Secret key for JWT | Random UUID |
| `JWT_EXPIRATION_MS` | JWT expiration time in ms | `86400000` (24h) |

## 🔐 Authentication

### Register a New User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "user",
  "email": "user@example.com",
  "password": "password123",
  "role": ["user"]
}
```

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password123"
}
```

### Access Protected Endpoints
```http
GET /api/tasks
Authorization: Bearer <your-jwt-token>
```

## 📚 API Documentation

Explore the API documentation using Swagger UI:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 🧪 Testing

Run unit and integration tests:
```bash
mvn test
```

## 🏗 Project Structure

```
src/
├── main/
│   ├── java/com/universal/
│   │   ├── config/           # Configuration classes
│   │   ├── controller/       # REST controllers
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── entity/           # JPA entities
│   │   ├── exception/        # Custom exceptions
│   │   ├── repository/       # Data access layer
│   │   ├── security/         # Security configurations
│   │   └── service/          # Business logic
│   └── resources/
│       ├── application.yml    # Application properties
│       └── static/           # Static resources
└── test/                     # Test files
```

## 📝 Database Schema

The application uses the following main entities:

- **User**: Stores user information and credentials
- **Role**: Defines user roles (ROLE_USER, ROLE_ADMIN)
- **Task**: Represents tasks that can be assigned to users

## 🔒 Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based authorization
- CSRF protection
- CORS configuration

## 🚀 Deployment

### Prerequisites
- Docker and Docker Compose
- PostgreSQL (for production)

### Production Deployment

1. Update `application-prod.yml` with your production database credentials
2. Build the Docker image:
   ```bash
   docker-compose -f docker-compose.prod.yml build
   ```
3. Start the services:
   ```bash
   docker-compose -f docker-compose.prod.yml up -d
   ```

## 🤝 Contributing

1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ✨ Show Your Support

Give a ⭐️ if this project helped you!

## 📞 Contact

Sidarth Dongre - sidhdongre358@gmail.com

Project Link: [https://github.com/Sidhdongre358/universal-app](https://github.com/Sidhdongre358/universal-app)
