# Quantity Measurement App

**Branch:** `feature/UC13-Arithmetic-DRY`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC13-Arithmetic-DRY](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC13-Arithmetic-DRY)

---

## UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations

### Overview

UC13 refactors the arithmetic implementation introduced in UC12 (addition, subtraction, division) to eliminate code duplication and strictly enforce the **DRY (Don’t Repeat Yourself)** principle.

Instead of repeating validation, base-unit conversion, and arithmetic logic across multiple methods, this use case introduces:

* A centralized validation helper
* A core arithmetic execution helper
* An `ArithmeticOperation` enum for clean operation dispatch

The public API remains unchanged. All behaviors from UC12 are preserved while the internal implementation is significantly optimized for maintainability, readability, and scalability.

---

### Features Implemented

* Introduced private `ArithmeticOperation` enum (ADD, SUBTRACT, DIVIDE)
* Implemented centralized validation method:
  `validateArithmeticOperands(...)`
* Implemented centralized base arithmetic method:
  `performBaseArithmetic(...)`
* Refactored `add()`, `subtract()`, and `divide()` to delegate to helper methods
* Eliminated duplicated validation and conversion logic
* Preserved backward compatibility (all UC12 tests pass unchanged)
* Maintained immutability and mathematical correctness
* Improved scalability for future operations (e.g., MULTIPLY)

---

### Preconditions

* UC12 arithmetic operations are fully functional
* All measurement units implement `IMeasurable`
* Existing unit tests from UC12 pass
* Public API signatures must remain unchanged
* Refactoring must not modify behavior

---

### Functionality

* Centralized null, category, and finiteness validation
* Centralized base-unit conversion logic
* Enum-based arithmetic dispatch using `DoubleBinaryOperator`
* Unified error handling across all operations
* Preserved implicit and explicit target unit behavior
* Maintained rounding rules for add/subtract
* Division continues to return dimensionless scalar (double)

---

### Internal Flow

**Example:**

```java
q1.subtract(q2, FEET);
```

Execution Flow:

```
validateArithmeticOperands(q2, FEET, true)
        ↓
performBaseArithmetic(q2, SUBTRACT)
        ↓
SUBTRACT.compute(base1, base2)
        ↓
convertFromBaseUnit(...)
        ↓
return new Quantity<>(result, FEET)
```

---

### Example (Behavior Unchanged from UC12)

**Input:**

```bash
new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES))  
new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES))  
new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET))  
```

**Output:**

```bash
Quantity(2.0, FEET)  
Quantity(9.5, FEET)  
1.0  
```

---

### Test Coverage

The following test scenarios are covered:

* Delegation verification for add/subtract/divide
* Validation consistency across all operations
* Cross-category arithmetic prevention
* Finiteness validation centralization
* Division-by-zero handling
* Enum-based operation dispatch correctness
* Rounding consistency
* Immutability preservation
* Backward compatibility with UC12
* Code duplication elimination verification
* Performance comparison with UC12
* Operation chaining validation
* Helper method encapsulation (private visibility)

All UC12 test cases pass without modification.

---

### Architectural Improvements

* Enforced DRY principle
* Single source of truth for validation logic
* Single source of truth for base conversion logic
* Improved separation of concerns
* Reduced method complexity
* Shorter, more readable public methods
* Cleaner enum-based operation dispatch
* Simplified future extensibility (e.g., MULTIPLY)

---

### Design Principles Applied

* DRY Principle
* Single Responsibility Principle
* Open/Closed Principle
* Encapsulation
* Enum-based polymorphism
* Functional Interfaces & Lambda Expressions
* Refactoring without behavioral change

---

### Architectural Impact

* No breaking changes
* Public API unchanged
* Behavior identical to UC12
* Improved maintainability and readability
* Scalable foundation for future arithmetic extensions

---
