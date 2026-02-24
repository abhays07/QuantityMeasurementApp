# Quantity Measurement App

**Branch:** `feature/UC6-UnitAddition`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC6-UnitAddition](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC6-UnitAddition)

---

## UC6: Addition of Two Length Units (Same Category)

### Overview

This use case extends the length measurement system by introducing addition operations between two length quantities. It allows adding measurements of different units such as feet, inches, yards, and centimeters by converting both values to a common base unit before performing addition. The result is returned in the unit of the first operand while preserving immutability and mathematical accuracy.

---

### Features Implemented

* Added addition functionality between length measurements
* Supported addition across feet, inches, yards, and centimeters
* Used centralized conversion factors for accurate arithmetic operations
* Returned results in the unit of the first operand
* Ensured validation of input values and units before performing addition

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two length measurements with valid units are provided for addition

---

### Functionality

* Accepts two Quantity objects with their respective units
* Converts both measurements into a common base unit
* Adds the converted values
* Converts the result back to the unit of the first operand
* Returns a new Quantity object representing the sum

---

### Example

**Input:**

```
add(Quantity(1.0, FEET), Quantity(12.0, INCHES))  
add(Quantity(1.0, YARDS), Quantity(3.0, FEET))  
add(Quantity(36.0, INCHES), Quantity(1.0, YARDS))  
add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES))  
```

**Output:**

```
Quantity(2.0, FEET)  
Quantity(2.0, YARDS)  
Quantity(72.0, INCHES)  
Quantity(~5.08, CENTIMETERS)  
```

---

### Test Coverage

The following test scenarios are covered:

* Addition between same units such as feet-to-feet and inches-to-inches
* Cross-unit addition such as feet-to-inches, yards-to-feet, and centimeters-to-inches
* Validation of commutative property in addition
* Addition with zero, negative, large, and small values
* Validation of invalid inputs such as null operands or invalid units

---
