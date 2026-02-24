# Quantity Measurement App

**Branch:** `feature/UC8-StandaloneUnit`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC8-StandaloneUnit](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC8-StandaloneUnit)

---

## UC8: Refactoring Unit Enum to Standalone with Conversion Responsibility

### Overview

This use case refactors the LengthUnit enum into a standalone top-level class and assigns it full responsibility for handling unit conversion logic. The QuantityLength class is simplified to delegate all conversion operations to the unit itself. This improves code structure, reduces coupling, and establishes a scalable architecture while preserving all functionality from previous use cases.

---

### Features Implemented

* Refactored LengthUnit enum into a standalone class
* Moved conversion logic from QuantityLength to LengthUnit
* Delegated conversion, equality, and addition operations to unit methods
* Improved separation of concerns between unit logic and quantity logic
* Maintained backward compatibility with all previous equality, conversion, and addition features

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Quantity objects with valid units (FEET, INCHES, YARDS, CENTIMETERS) are provided

---

### Functionality

* Uses standalone LengthUnit enum for unit conversion
* Converts values using unit.convertToBaseUnit() and convertFromBaseUnit() methods
* Performs equality comparison using delegated conversion logic
* Supports addition and conversion operations using unit-based conversion
* Returns accurate and immutable Quantity objects

---

### Example

**Input:**

```id="uc8ex1"
Quantity(1.0, FEET).convertTo(INCHES)  
Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)  
Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))  
LengthUnit.INCHES.convertToBaseUnit(12.0)  
```

**Output:**

```id="uc8ex2"
Quantity(12.0, INCHES)  
Quantity(2.0, FEET)  
true  
1.0  
```

---

### Test Coverage

The following test scenarios are covered:

* Validation of standalone LengthUnit enum conversion methods
* Equality, conversion, and addition using delegated unit logic
* Backward compatibility with UC1–UC7 functionality
* Validation of conversion accuracy across all supported units
* Validation of invalid inputs such as null unit or invalid values

---
