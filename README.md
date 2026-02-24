# Quantity Measurement App

**Branch:** `feature/UC2-FeetAndInchesEquality`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC2-FeetAndInchesEquality](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC2-FeetAndInchesEquality)

---

## UC2: Feet and Inches Measurement Equality

### Overview

This use case extends UC1 by introducing equality comparison for measurements in inches in addition to feet. It implements a separate `Inches` class similar to the `Feet` class and compares values using value-based equality. Both measurement types are handled independently, ensuring accurate floating-point comparison and proper object equality handling.

---

### Features Implemented

* Encapsulated feet and inches measurements using dedicated `Feet` and `Inches` classes
* Implemented value-based equality using overridden `equals()` method in both classes
* Used `Double.compare()` for precise floating-point comparison
* Ensured type safety and null safety during comparison
* Provided separate validation and equality checks for feet and inches measurements

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two numeric values in feet and two numeric values in inches are provided for comparison

---

### Functionality

* Creates `Feet` objects and compares them using the overridden `equals()` method
* Creates `Inches` objects and compares them using the overridden `equals()` method
* Ensures only objects of the same type are compared
* Returns `true` if both values are equal, otherwise `false`

---

### Example

**Input:**

```
1.0 ft and 1.0 ft  
1.0 inch and 1.0 inch  
```

**Output:**

```
Equal (true)  
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

---
