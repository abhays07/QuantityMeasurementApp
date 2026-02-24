# Quantity Measurement App

**Branch:** `feature/UC1-FeetEquality`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC1-FeetEquality](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC1-FeetEquality)

---

## UC1: Feet Measurement Equality

### Overview

This use case implements equality comparison for measurements in feet. It introduces a `Feet` class to represent measurements and compares two values using value-based equality. The implementation ensures accurate floating-point comparison and proper object equality handling.

---

### Features Implemented

* Encapsulated feet measurement using a dedicated `Feet` class
* Implemented value-based equality using overridden `equals()` method
* Used `Double.compare()` for precise floating-point comparison
* Ensured type safety and null safety during comparison
* Provided equality validation through the main application

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two numeric values in feet are provided for comparison

---

### Functionality

* Creates two `Feet` objects with measurement values
* Compares both objects using the overridden `equals()` method
* Ensures only objects of the same type are compared
* Returns `true` if both values are equal, otherwise `false`

---

### Example

**Input:**

```
1.0 ft and 1.0 ft
```

**Output:**

```
Equal (true)
```

---

### Test Coverage

The following test scenarios are covered:

* Equality when both values are the same
* Inequality when values are different
* Comparison with null object
* Same reference comparison
* Type safety validation
