# Quantity Measurement App

**Branch:** `feature/UC9-WeightMeasurement`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC9-WeightMeasurement](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC9-WeightMeasurement)

---

## UC9: Weight Measurement Equality, Conversion, and Addition

### Overview

This use case extends the application to support weight measurements alongside length measurements. It introduces a standalone WeightUnit enum and QuantityWeight class to handle weight equality, unit conversion, and arithmetic addition. The design follows the same scalable architecture as length measurement, ensuring consistency and separation between measurement categories.

---

### Features Implemented

* Introduced standalone WeightUnit enum with KILOGRAM, GRAM, and POUND
* Implemented QuantityWeight class for weight measurement operations
* Supported equality comparison across different weight units
* Enabled conversion between kilogram, gram, and pound
* Implemented addition operations with implicit and explicit target units

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Weight measurements with valid units (KILOGRAM, GRAM, POUND) are provided

---

### Functionality

* Compares weight measurements using base unit normalization (kilogram)
* Converts weight measurements between supported units
* Adds weight measurements with automatic unit conversion
* Supports explicit target unit specification for addition
* Ensures type safety by preventing comparison between length and weight

---

### Example

**Input:**

```bash
Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM))  
Quantity(1.0, KILOGRAM).convertTo(POUND)  
Quantity(500.0, GRAM).add(Quantity(0.5, KILOGRAM))  
Quantity(1.0, POUND).add(Quantity(453.592, GRAM), POUND)  
```

**Output:**

```bash
true  
Quantity(~2.20462, POUND)  
Quantity(1000.0, GRAM)  
Quantity(~2.0, POUND)  
```

---

### Test Coverage

The following test scenarios are covered:

* Equality comparison between same and different weight units
* Conversion between kilogram, gram, and pound
* Addition with implicit and explicit target units
* Validation of weight and length category incompatibility
* Validation of invalid inputs, null units, and edge cases

---
