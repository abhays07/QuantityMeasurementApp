# Quantity Measurement App

**Branch:** `feature/UC16-JDBCPersistence`
**Code Link:**
[https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC16-JDBCPersistence](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC16-JDBCPersistence)

---

# UC16: Database Integration with JDBC for Quantity Measurement Persistence

## Overview

UC16 enhances the Quantity Measurement Application by introducing **persistent database storage using JDBC (Java Database Connectivity)**.

Building upon the **N-Tier architecture introduced in UC15**, this update replaces the in-memory repository with a **database-backed repository**, enabling long-term storage of quantity measurement operations.

This architectural improvement enables:

* Persistent storage of measurement history
* Historical audit tracking
* Database querying of measurement operations
* Enterprise-level project structure using Maven
* Configurable repository selection (Cache or Database)

The system now supports **professional backend architecture practices** used in real-world enterprise applications.

---

# Architecture Layers

## Application Layer

Represents the **entry point of the application**.

**Class:**

```
QuantityMeasurementApp
```

Responsibilities:

* Initializes controller, service, and repository
* Reads configuration from `application.properties`
* Determines repository type (cache or database)
* Coordinates application startup
* Delegates execution to the controller

The application now supports **configurable persistence implementation**.

---

## Controller Layer

Handles **application orchestration and request processing**.

**Class:**

```
QuantityMeasurementController
```

Responsibilities:

* Accepts quantity requests using `QuantityDTO`
* Validates request structure
* Delegates operations to the service layer
* Formats and returns results
* Handles user-level output

The controller continues to act as a **Facade layer** between user input and business logic.

---

## Service Layer

Contains the **core quantity measurement business logic**.

**Classes:**

```
IQuantityMeasurementService
QuantityMeasurementServiceImpl
```

Responsibilities:

* Quantity comparison
* Unit conversion
* Arithmetic operations
* Cross-category validation
* Error handling
* Repository interaction

The service layer **remains independent of persistence implementation**, interacting only through the repository interface.

---

## Repository Layer

Handles **data persistence and storage abstraction**.

**Classes:**

```
IQuantityMeasurementRepository
QuantityMeasurementCacheRepository
QuantityMeasurementDatabaseRepository
```

Responsibilities:

* Persist measurement operations
* Store operation history
* Query measurement records
* Manage database connections
* Provide statistics about stored measurements

UC16 introduces a new repository implementation:

```
QuantityMeasurementDatabaseRepository
```

which stores measurement operations using **JDBC and SQL queries**.

---

## Entity / Model Layer

Defines **data structures shared across application layers**.

### QuantityDTO

Represents external request and response objects.

Used for:

* Controller → Service communication
* Service → Controller responses
* Future REST API requests

---

### QuantityModel

Represents internal quantity values used by the service layer.

Provides:

* Type-safe measurement operations
* Unit conversion handling
* Category validation

---

### QuantityMeasurementEntity

Represents a **complete record of a measurement operation**.

Stores:

* Operation type
* Operand values
* Result value
* Error status
* Timestamp metadata

Entities are now **persisted in a relational database**.

---

# Features Implemented

* Introduced **JDBC-based database persistence**
* Implemented **QuantityMeasurementDatabaseRepository**
* Added **connection pooling for efficient database access**
* Introduced **ApplicationConfig for configuration management**
* Implemented **ConnectionPool utility for database connections**
* Added **DatabaseException for database-specific errors**
* Implemented **SQL schema initialization**
* Added **parameterized SQL queries to prevent SQL injection**
* Introduced **Maven project structure**
* Added **SLF4J logging and Logback configuration**
* Implemented **test database using H2**
* Added **integration testing with database persistence**
* Enabled switching between **cache repository and database repository**
* Maintained full backward compatibility with **UC1–UC15**

---

# Data Flow

Example: **Addition Operation**

```
User Request
      ↓
Controller (QuantityMeasurementController)
      ↓
Service (QuantityMeasurementServiceImpl)
      ↓
Business Logic Execution
      ↓
Create QuantityMeasurementEntity
      ↓
Repository (Database Repository)
      ↓
Persist Entity in Database
      ↓
Return QuantityDTO Response
```

---

# Supported Operations

All operations from **UC1–UC15** remain fully supported:

* Equality comparison
* Unit conversion
* Addition
* Subtraction
* Division
* Temperature measurement validation
* Cross-category comparison prevention

All operations are now **persisted automatically in the database repository**.

---

# Database Integration

UC16 introduces **relational database storage** for measurement operations.

### Database Table

```
quantity_measurements
```

Stores:

* measurement type
* operands
* operation type
* result
* error status
* timestamps

The schema is initialized automatically during application startup.

---

# JDBC Implementation

Database operations are executed using **PreparedStatement**.

Example:

```java
PreparedStatement stmt = connection.prepareStatement(
"INSERT INTO quantity_measurements(operation, operand1, operand2, result) VALUES (?, ?, ?, ?)"
);
```

Benefits:

* SQL injection protection
* Efficient query execution
* Safe parameter binding

---

# Connection Pooling

UC16 introduces **ConnectionPool** for efficient database connection management.

Benefits:

* Reduces connection creation overhead
* Supports concurrent database access
* Improves performance
* Prevents connection exhaustion

---

# Maven Project Structure

UC16 refactors the project into a **standard Maven layout**.

```
src/main/java
src/main/resources
src/test/java
pom.xml
```

Dependencies added:

* H2 Database
* SLF4J
* Logback
* JUnit
* Mockito

Common Maven commands:

Compile:

```
mvn clean compile
```

Run application:

```
mvn exec:java
```

Run tests:

```
mvn test
```

Create executable JAR:

```
mvn clean package
```

---

# Design Patterns Applied

UC16 continues using enterprise design patterns introduced in UC15.

### Repository Pattern

Provides abstraction for data persistence.

---

### Singleton Pattern

Used for repository and connection pool instances.

---

### Factory Pattern

Creates appropriate repository implementation based on configuration.

---

### Facade Pattern

Implemented via the controller layer.

---

### Dependency Injection

Repository injected into the service layer via constructor.

Benefits:

* Loose coupling
* Testability
* Framework compatibility

---

# Architectural Improvements over UC15

UC15 introduced N-Tier architecture but still relied on **in-memory storage**.

UC16 resolves these limitations:

| Problem in UC15          | Solution in UC16              |
| ------------------------ | ----------------------------- |
| In-memory storage        | Database persistence          |
| Data lost on restart     | Persistent database storage   |
| Limited query capability | SQL query support             |
| No concurrency support   | Connection pooling            |
| Difficult reporting      | Database analytics capability |
| File-based serialization | Relational schema storage     |

---

# Benefits of UC16

### Persistent Data Storage

Measurement history stored permanently in database.

---

### Scalability

Supports multiple application instances accessing the same database.

---

### Query Capability

Measurement records can be filtered and analyzed using SQL.

---

### Production Readiness

Application now follows real-world backend architecture patterns.

---

### Maintainability

Maven structure improves project organization and dependency management.

---

# Example Output

### Saving Measurement

```
Comparing 3 feet and 36 inches → true
Operation stored in database
```

### Querying Measurements

```
Retrieved 5 measurement records
```

### Connection Pool Status

```
Active connections: 2
Idle connections: 8
```

---

# Test Coverage

UC16 introduces new **unit tests and integration tests**.

Coverage includes:

* Database repository operations
* Connection pool behavior
* JDBC query execution
* SQL injection prevention
* Service layer integration
* Controller layer integration
* Maven build verification
* End-to-end database persistence tests

All **UC1–UC15 tests continue to pass without modification.**

---

# Architectural Impact

UC16 introduces **no breaking changes**.

Existing application behavior remains unchanged while adding **database persistence capabilities**.

The system is now:

* Database-backed
* Modular
* Enterprise-ready
* Scalable
* Ready for REST APIs and Spring Boot integration

---

# Key Concepts Learned

* JDBC Programming
* Database Persistence
* Connection Pooling
* Maven Project Structure
* Repository Pattern
* Configuration Management
* Integration Testing
* Logging Frameworks
* SQL Query Design
* Enterprise Backend Architecture

