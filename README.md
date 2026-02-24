# Quantity Measurement App

**Branch:** `feature/UC10-GenericQuantity`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity)

---

## UC10: Generic Quantity Class with Unit Interface for Multi-Category Support

### Overview

This use case refactors the application by introducing a generic `Quantity<U>` class and a common `IMeasurable` interface to support multiple measurement categories such as length and weight. This eliminates duplication across category-specific classes and establishes a scalable architecture. The design ensures consistent equality comparison, conversion, and addition operations while maintaining strict type safety across different measurement categories.

---

### Features Implemented

* Introduced `IMeasurable` interface to standardize unit behavior
* Implemented generic `Quantity<U extends IMeasurable>` class for all measurement categories
* Refactored `LengthUnit` and `WeightUnit` enums to implement `IMeasurable`
* Supported equality, conversion, and addition using a single generic implementation
* Ensured compile-time and runtime type safety between different measurement categories

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Measurement values with valid unit enums implementing `IMeasurable` are provided

---

### Functionality

* Compares quantities using base unit normalization through generic implementation
* Converts quantities between supported units using unit interface methods
* Adds quantities with implicit and explicit target unit support
* Prevents cross-category comparisons using generic type safety and runtime validation
* Supports scalable integration of new measurement categories without modifying core logic

---

### Example

**Input:**

```bash
new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(12.0, LengthUnit.INCHES))  
new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM)  
new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET)  
new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM)  
```

**Output:**

```bash
true  
Quantity(1000.0, GRAM)  
Quantity(2.0, FEET)  
Quantity(2.0, KILOGRAM)  
```

---

### Test Coverage

The following test scenarios are covered:

* Generic equality comparison across multiple measurement categories
* Unit conversion using generic Quantity class and unit interface
* Addition with implicit and explicit target units using generic implementation
* Prevention of cross-category comparison using generic type safety
* Validation of constructor inputs, null units, and invalid values

---
