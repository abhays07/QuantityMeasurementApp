# Quantity Measurement App

**Branch:** `feature/UC3-GenericLength`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC3-GenericLength](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC3-GenericLength)

---

## UC3: Generic Quantity Class for DRY Principle

### Overview

This use case refactors the existing Feet and Inches implementation into a single generic `QuantityLength` class using a `LengthUnit` enum. It eliminates code duplication and enables equality comparison across different length units by converting values into a common base unit. This ensures accurate floating-point comparison and improves scalability for supporting additional measurement units.

---

### Features Implemented

* Introduced a generic `QuantityLength` class to represent length measurements
* Implemented `LengthUnit` enum to define supported units and conversion factors
* Implemented value-based equality using overridden `equals()` method
* Used conversion to a common base unit for accurate cross-unit comparison
* Ensured type safety, null safety, and unit validation during comparison

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Two numeric values with their respective length units are provided for comparison

---

### Functionality

* Creates `QuantityLength` objects with value and unit type
* Converts measurements to a common base unit before comparison
* Compares objects using the overridden `equals()` method
* Returns `true` if both values represent equal measurements, otherwise `false`

---

### Example

**Input:**

```id="j8e6ho"
Quantity(1.0, FEET) and Quantity(12.0, INCHES)  
Quantity(1.0, INCHES) and Quantity(1.0, INCHES)
```

**Output:**

```id="p4vsyc"
Equal (true)  
Equal (true)
```

---

### Test Coverage

The following test scenarios are covered:

* Equality when both values are the same unit and value
* Equality when values are equivalent across different units
* Inequality when values are different
* Comparison with null object
* Same reference comparison and unit validation

---
