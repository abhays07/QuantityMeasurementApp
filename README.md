# Quantity Measurement App

**Branch:** `feature/UC18-GoogleAuth-JWT`
**Code Link:**
[https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC18-GoogleAuth-JWT](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC18-GoogleAuth-JWT)

---

# UC18: Google Authentication and JWT-based User Management for Quantity Measurement

## Overview

UC18 enhances the Quantity Measurement Spring Boot application by introducing **authentication and authorization using Spring Security, JWT, and Google OAuth2 login**.

This use case builds upon **UC17 REST-based architecture** and adds secure user management capabilities, enabling authenticated access to quantity measurement APIs.

The system now supports:

* JWT-based authentication
* Google OAuth2 login
* Spring Security configuration
* User registration and login APIs
* Protected REST endpoints
* Role-based access foundation
* Token validation filter
* Secure API access

The application is now **production-ready with secure authentication mechanisms**.

---

# Architecture Layers

## Application Layer

Spring Boot application remains unchanged.

**Class:**

```
QuantityMeasurementApplication
```

Responsibilities:

* Bootstraps Spring Boot
* Initializes security configuration
* Loads OAuth and JWT settings

---

## Security Layer (New in UC18)

Handles **authentication and authorization**.

**Classes Introduced:**

```
SecurityConfig
JwtAuthenticationFilter
JwtUtil
UserDetailsServiceImpl
```

Responsibilities:

* JWT token validation
* Authentication filter chain
* OAuth2 login configuration
* Password encoding
* Endpoint authorization rules

---

## Controller Layer

New controllers added for authentication.

**Classes:**

```
AuthController
OAuthController
QuantityMeasurementController
```

Responsibilities:

* User registration
* User login
* Google OAuth login
* Token generation
* Secure endpoint access

---

## Service Layer

Authentication services added.

**Classes:**

```
AuthService
QuantityMeasurementService
```

Responsibilities:

* User authentication
* Token generation
* User validation
* Business logic execution

---

## Repository Layer

New repository added for users.

**Interfaces:**

```
UserRepository
QuantityMeasurementRepository
```

Responsibilities:

* Store user data
* Retrieve user by email
* Persist measurement operations

---

## Model Layer

New user entity introduced.

### User Entity

Represents authenticated user.

Fields:

* id
* name
* email
* password
* provider
* roles

---

### DTO Classes Added

```
AuthRequest
AuthResponse
UserRegistrationDTO
```

Used for:

* Login requests
* JWT responses
* User registration

---

# Features Implemented

* Spring Security integration
* JWT authentication
* Google OAuth2 login
* User registration API
* Login API
* Token generation
* Token validation filter
* Password encryption
* Protected REST endpoints
* OAuth success handler
* Custom UserDetailsService
* Authentication manager configuration
* Stateless session management
* Secure API access
* Role-based access foundation
* Integration with UC17 REST APIs

---

# Security Flow

### JWT Authentication Flow

```
User Login Request
        ↓
AuthController
        ↓
AuthenticationManager
        ↓
JwtUtil generate token
        ↓
Return JWT token
        ↓
Client sends token in header
        ↓
JwtAuthenticationFilter
        ↓
Validate token
        ↓
Access secured endpoints
```

---

# Google OAuth2 Flow

```
User clicks Google Login
        ↓
Spring OAuth2
        ↓
Google Authentication
        ↓
OAuthController
        ↓
Generate JWT Token
        ↓
Return token to client
```

---

# Security Configuration

Configured using:

```
SecurityConfig
```

Key configurations:

* Disable CSRF
* Stateless session
* Permit public endpoints
* Secure API endpoints
* OAuth2 login enabled
* JWT filter added

---

# JWT Utility

**Class:**

```
JwtUtil
```

Responsibilities:

* Generate token
* Validate token
* Extract username
* Handle expiration

Token contains:

* subject (email)
* issued date
* expiration time
* signature

---

# Authentication Endpoints

### Register User

```
POST /api/auth/register
```

---

### Login User

```
POST /api/auth/login
```

---

### Google OAuth Login

```
GET /oauth2/authorization/google
```

---

# Protected APIs

All Quantity Measurement APIs now require authentication:

```
/api/v1/quantities/**
```

Token required in header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Example Authentication Flow

### Login Request

```
POST /api/auth/login
{
  "email": "user@gmail.com",
  "password": "password"
}
```

---

### Response

```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### Access Secured API

```
GET /api/v1/quantities/history
Authorization: Bearer <token>
```

---

# Google OAuth Example

User visits:

```
http://localhost:8080/oauth2/authorization/google
```

After login:

```
JWT token generated
```

---

# Password Security

Passwords encrypted using:

```
BCryptPasswordEncoder
```

Benefits:

* Secure hashing
* No plaintext storage
* Production safe

---

# Dependencies Added

* Spring Security
* Spring OAuth2 Client
* JWT (jjwt)
* Spring Boot Starter Security
* Spring Boot Starter OAuth2
* Lombok

---

# Architectural Improvements over UC17

| UC17               | UC18                     |
| ------------------ | ------------------------ |
| Open REST APIs     | Secured REST APIs        |
| No authentication  | JWT authentication       |
| No OAuth           | Google OAuth login       |
| No user management | User entity & repository |
| No token security  | JWT token validation     |
| No role support    | Role-based foundation    |

---

# Benefits of UC18

### Secure APIs

All endpoints protected.

### JWT Authentication

Stateless and scalable.

### Google OAuth Login

Third-party authentication support.

### User Management

User registration and login.

### Spring Security Integration

Enterprise-level security.

### Stateless Sessions

Improved scalability.

---

# Example Output

### Register User

```
User registered successfully
```

---

### Login

```
JWT token generated
```

---

### Access Protected API

```
Authorization successful
```

---

# Test Coverage

UC18 includes:

* Authentication controller tests
* JWT utility tests
* Security filter tests
* OAuth login tests
* Protected endpoint tests
* Integration tests

All **UC1–UC17 functionality remains unchanged**.

---

# Architectural Impact

UC18 introduces **security layer** while keeping business logic intact.

Application is now:

* Secure
* OAuth-enabled
* JWT-enabled
* Production-ready
* Scalable
* Enterprise-ready

---
