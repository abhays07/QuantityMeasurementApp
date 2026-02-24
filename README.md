# Quantity Measurement App

**Branch:** `feature/UC4-YardEquality`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC4-YardEquality](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC4-YardEquality)

---

## UC4: Extended Unit Support

### Overview

This use case extends the generic `QuantityLength` implementation by adding support for Yards and Centimeters as additional length units. The system allows equality comparison across feet, inches, yards, and centimeters by converting values into a common base unit. The implementation preserves the generic design introduced earlier and enables seamless addition of new units without code duplication.

---

### Features Implemented

* Added YARDS and CENTIMETERS to the `LengthUnit` enum with proper conversion factors
* Supported equality comparison across feet, inches, yards, and centimeters
* Used conversion to a common base unit for accurate equality comparison
* Maintained generic design without modifying the core quantity logic
* Ensured type safety, null safety, and unit validation

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two numeric values with their respective supported length units are provided for comparison

---

### Functionality

* Creates `QuantityLength` objects using feet, inches, yards, or centimeters
* Converts measurements to a common base unit using defined conversion factors
* Compares objects using the overridden `equals()` method
* Returns `true` if both values represent equal measurements, otherwise `false`

---

### Example

**Input:**

```
Quantity(1.0, YARDS) and Quantity(3.0, FEET)
Quantity(1.0, YARDS) and Quantity(36.0, INCHES)
Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)
```

**Output:**

```
Equal (true)
Equal (true)
Equal (true)
```

---

### Test Coverage

The following test scenarios are covered:

* Equality when both values are the same unit (yard-to-yard, centimeter-to-centimeter)
* Equality when values are equivalent across different units (yard-to-feet, yard-to-inches, centimeter-to-inches)
* Inequality when values are different across units
* Comparison with null object
* Same reference comparison and unit validation

---
