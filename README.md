# Quantity Measurement App

**Branch:** `feature/UC7-TargetUnitAddition`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC7-TargetUnitAddition/](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC7-TargetUnitAddition/)

---

## UC7: Addition with Target Unit Specification

### Overview

This use case extends the length addition functionality by allowing explicit specification of the target unit for the result. Instead of defaulting to the unit of the first operand, the addition method now accepts a target unit parameter, enabling flexible result representation across feet, inches, yards, and centimeters while maintaining accuracy and immutability.

---

### Features Implemented

* Added support for specifying a target unit during addition
* Allowed addition across feet, inches, yards, and centimeters with flexible output unit
* Used centralized conversion logic to ensure mathematical correctness
* Returned results strictly in the explicitly specified target unit
* Ensured validation of operands and target unit before performing addition

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two Quantity objects and a valid target unit are provided for addition

---

### Functionality

* Accepts two Quantity objects and a target unit
* Converts both measurements into a common base unit
* Adds the converted values
* Converts the result into the specified target unit
* Returns a new Quantity object representing the result

---

### Example

**Input:**

```id="1w5lsm"
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), FEET)  
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), INCHES)  
add(Quantity(1.0, FEET), Quantity(12.0, INCHES), YARDS)  
add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES), CENTIMETERS)  
```

**Output:**

```id="6ds3v5"
Quantity(2.0, FEET)  
Quantity(24.0, INCHES)  
Quantity(~0.667, YARDS)  
Quantity(~5.08, CENTIMETERS)  
```

---

### Test Coverage

The following test scenarios are covered:

* Addition with explicitly specified target unit matching operands
* Addition with target unit different from both operands
* Validation of commutative property with target unit specification
* Addition with zero, negative, large, and small values
* Validation of invalid inputs such as null target unit or invalid unit

---
