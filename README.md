# Quantity Measurement App

**Branch:** `feature/UC15-NTierArchitectureRefactor`
**Code Link:**
[https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC15-NTierArchitectureRefactor](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC15-NTierArchitectureRefactor)

---

# UC15: N-Tier Architecture Refactoring for Quantity Measurement Application

## Overview

UC15 refactors the Quantity Measurement Application from a **monolithic structure** into a **professional N-Tier architecture**.
The refactoring introduces clear **layer separation** between the Application, Controller, Service, Repository, and Entity layers.

This architectural transformation improves:

* Maintainability
* Testability
* Scalability
* Code organization
* Separation of concerns

The system now follows modern enterprise architecture practices and prepares the application for **future extensions such as REST APIs, persistence frameworks, and dependency injection frameworks (Spring/Guice).**

---

# Architecture Layers

## Application Layer

Represents the **entry point** of the system.

**Class:**

```
QuantityMeasurementApp
```

Responsibilities:

* Initializes controller, service, and repository
* Configures dependency injection
* Coordinates overall application flow
* Delegates all business operations to the controller

---

## Controller Layer

Handles **user interaction and orchestration**.

**Class:**

```
QuantityMeasurementController
```

Responsibilities:

* Accepts requests using `QuantityDTO`
* Validates input structure
* Delegates operations to the service layer
* Formats output results
* Handles presentation concerns

The controller acts as a **Facade** simplifying complex service interactions.

---

## Service Layer

Contains the **core business logic**.

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
* Business rule enforcement
* Error handling

The service layer follows **Single Responsibility Principle (SRP)** and **Open/Closed Principle (OCP)**.

---

## Repository Layer

Handles **data persistence and storage abstraction**.

**Classes:**

```
IQuantityMeasurementRepository
QuantityMeasurementCacheRepository
```

Responsibilities:

* Store quantity operation results
* Maintain measurement history
* Provide in-memory caching
* Persist data through serialization

The repository uses a **Singleton pattern** to ensure centralized storage.

---

## Entity / Model Layer

Defines **data structures used across layers**.

### QuantityDTO

Represents external input/output structure.

Used for:

* Controller → Service communication
* Service → Controller responses
* Future REST API payloads

---

### QuantityModel

Internal representation of quantity values used by the service layer.

Provides:

* Generic type safety
* Measurement category enforcement
* Operational logic support

---

### QuantityMeasurementEntity

Represents a **complete measurement operation record**.

Stores:

* Operand quantities
* Operation type
* Result
* Error state
* Metadata

This entity is **Serializable** and stored in the repository for history tracking.

---

# Features Implemented

* Refactored monolithic architecture into **N-Tier architecture**
* Introduced **Controller layer for orchestration**
* Introduced **Service layer for business logic**
* Introduced **Repository layer for persistence**
* Implemented **DTO and Model classes for data flow**
* Implemented **QuantityMeasurementEntity for operation history**
* Implemented **Dependency Injection for service-repository integration**
* Implemented **Factory pattern for service/controller creation**
* Implemented **Singleton repository for centralized storage**
* Implemented **Facade pattern through controller interface**
* Added **custom QuantityMeasurementException for error handling**
* Ensured full backward compatibility with **UC1–UC14**

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
Save in Repository
      ↓
Return QuantityDTO Response
```

---

# Supported Operations

The system supports all operations from **UC1–UC14**:

* Equality comparison
* Unit conversion
* Addition
* Subtraction
* Division
* Temperature measurement with restricted arithmetic
* Cross-category validation

All operations now execute through the **Service layer**.

---

# Design Patterns Applied

UC15 integrates several enterprise-level design patterns:

### Singleton Pattern

Used for:

```
QuantityMeasurementCacheRepository
```

Ensures one centralized repository instance.

---

### Factory Pattern

Used for creating:

* Controller instances
* Service implementations

Provides loose coupling between layers.

---

### Facade Pattern

Implemented through:

```
QuantityMeasurementController
```

Simplifies interaction with complex service logic.

---

### Dependency Injection

Service receives repository dependency via constructor injection.

Benefits:

* Loose coupling
* Easy testing
* Framework readiness

---

### Interface Segregation Principle (ISP)

Separate interfaces defined for:

* Service operations
* Repository operations

Clients depend only on required functionality.

---

# Architectural Improvements over UC14

UC14 introduced temperature support but still suffered from monolithic design problems.

UC15 resolves these issues by:

| Problem in UC14          | Solution in UC15       |
| ------------------------ | ---------------------- |
| Mixed responsibilities   | Layered architecture   |
| Business logic inside UI | Service layer          |
| Hardcoded method calls   | Controller abstraction |
| Difficult unit testing   | Service isolation      |
| No data contracts        | DTO and Entity classes |
| Tight coupling           | Dependency injection   |
| Difficult scalability    | Modular design         |

---

# Benefits of UC15

### Separation of Concerns

Each layer handles a single responsibility.

---

### Improved Testability

* Service logic testable independently
* Controller testable with mock services

---

### Scalability

Future integrations supported:

* REST APIs
* CLI interfaces
* GUI applications
* Mobile clients

---

### Maintainability

Changes to one layer do not affect others.

---

### Reusability

Service layer can be reused by multiple clients.

---

# Example Output

### Length Equality

```
3 feet equals 36 inches → true
```

### Conversion

```
10 feet → 3.048 meters
```

### Addition

```
3 feet + 24 inches → 5 feet
```

### Temperature Validation

```
100°C + 50°C → UnsupportedOperationException
```

---

# Test Coverage

UC15 maintains full compatibility with all previous test suites.

Coverage includes:

* Entity construction validation
* Service layer arithmetic operations
* Unit conversion logic
* Cross-category validation
* Temperature arithmetic restrictions
* Controller orchestration
* Repository storage
* Error handling propagation
* Layer separation verification
* End-to-end integration scenarios

All **UC1–UC14 tests pass without modification.**

---

# Architectural Impact

UC15 introduces **no breaking changes**.

Existing functionality remains unchanged while the internal architecture is significantly improved.

The application is now:

* Modular
* Extensible
* Enterprise-ready
* Framework-compatible

---

# Key Concepts Learned

* N-Tier Architecture
* Separation of Concerns
* DTO Pattern
* Repository Pattern
* Dependency Injection
* SOLID Principles
* Design Patterns Integration
* Layered Application Design
* Enterprise Software Architecture
