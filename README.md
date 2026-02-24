# Quantity Measurement App

**Branch:** `feature/UC5-UnitConversion`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC5-UnitConversion](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC5-UnitConversion)

---

## UC5: Unit-to-Unit Conversion (Same Measurement Type)

### Overview

This use case extends the existing length measurement system by introducing explicit unit-to-unit conversion functionality. It allows conversion between feet, inches, yards, and centimeters using centralized conversion factors. The implementation provides a conversion API that returns mathematically equivalent values in the specified target unit while preserving precision and validation.

---

### Features Implemented

* Added conversion functionality between supported length units
* Supported conversions across feet, inches, yards, and centimeters
* Used centralized conversion factors defined in the `LengthUnit` enum
* Implemented validation for numeric values and valid units
* Ensured precision and mathematical consistency during conversion

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* A numeric value, source unit, and target unit are provided for conversion

---

### Functionality

* Accepts a numeric value with a source unit and target unit
* Converts the value into a common base unit
* Converts the base unit value into the target unit
* Returns the converted numeric result
* Ensures invalid inputs are rejected through validation

---

### Example

**Input:**

```id="yujp0r"
convert(1.0, FEET, INCHES)
convert(3.0, YARDS, FEET)
convert(36.0, INCHES, YARDS)
convert(2.54, CENTIMETERS, INCHES)
```

**Output:**

```id="yxh16p"
12.0
9.0
1.0
~1.0
```

---

### Test Coverage

The following test scenarios are covered:

* Conversion between same units and different units
* Cross-unit conversions such as feet-to-inches, yards-to-feet, and centimeters-to-inches
* Round-trip conversion accuracy validation
* Conversion of zero and negative values
* Validation of invalid inputs such as null units, NaN, and infinite values

---
