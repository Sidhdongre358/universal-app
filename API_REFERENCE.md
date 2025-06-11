# API Reference

## Base URL
```
http://localhost:8080/api
```

## Authentication
All endpoints except `/auth/**` require a valid JWT token in the `Authorization` header.

### Headers
```
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

## Auth Endpoints

### Register a New User
```http
POST /auth/register
```

**Request Body**
```json
{
  "username": "user",
  "email": "user@example.com",
  "password": "password123",
  "role": ["user"]
}
```

### Login
```http
POST /auth/login
```

**Request Body**
```json
{
  "username": "user",
  "password": "password123"
}
```

**Response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "user",
  "email": "user@example.com",
  "roles": ["ROLE_USER"]
}
```

## Task Endpoints

### Get All Tasks
```http
GET /tasks
```

**Query Parameters**
- `page`: Page number (default: 0)
- `size`: Items per page (default: 10)
- `sort`: Sort by field (e.g., `dueDate,desc`)

### Get Task by ID
```http
GET /tasks/{id}
```

### Create Task
```http
POST /tasks
```

**Request Body**
```json
{
  "title": "Complete project documentation",
  "description": "Write detailed API documentation",
  "dueDate": "2025-06-30",
  "status": "PENDING"
}
```

### Update Task
```http
PUT /tasks/{id}
```

### Delete Task
```http
DELETE /tasks/{id}
```

## User Endpoints

### Get Current User Profile
```http
GET /users/me
```

### Update Profile
```http
PUT /users/me
```

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2025-06-11T18:50:12.123+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/tasks"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2025-06-11T18:50:12.123+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/api/tasks"
}
```

### 404 Not Found
```json
{
  "timestamp": "2025-06-11T18:50:12.123+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found with id: 999",
  "path": "/api/tasks/999"
}
```

## Rate Limiting
- 100 requests per minute per IP for authenticated users
- 20 requests per minute per IP for unauthenticated users
