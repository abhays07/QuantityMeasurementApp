# Quantity Measurement App

**Branch:** `feature/UC12-SubstractionDivision`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC12-SubstractionDivision](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC12-SubstractionDivision)

---

## UC12: Subtraction and Division Operations on Quantity Measurements

### Overview

This use case extends the generic `Quantity<U extends IMeasurable>` implementation by introducing **subtraction** and **division** operations. Building upon equality, conversion, and addition (UC1–UC11), this enhancement enables full arithmetic manipulation of measurements across length, weight, and volume categories.

The implementation maintains immutability, cross-unit compatibility, strict type safety, and comprehensive validation while preserving backward compatibility with all previous use cases.

---

### Features Implemented

* Added overloaded `subtract()` methods (implicit and explicit target unit support)
* Added `divide()` method returning dimensionless scalar (double)
* Enabled cross-unit subtraction and division within the same category
* Maintained immutability — original objects remain unchanged
* Implemented strict cross-category prevention (Length ≠ Weight ≠ Volume)
* Added division-by-zero validation
* Preserved backward compatibility with UC1–UC11
* Ensured rounding consistency (2 decimal places for subtraction)

---

### Preconditions

* Generic `Quantity<U extends IMeasurable>` class is fully functional
* `LengthUnit`, `WeightUnit`, and `VolumeUnit` implement `IMeasurable`
* Base unit conversion logic is correctly implemented
* Existing equality and addition features remain intact

---

### Functionality

* Subtracts quantities with automatic base-unit normalization
* Supports explicit target unit specification in subtraction
* Divides quantities to compute a dimensionless ratio
* Prevents invalid cross-category arithmetic operations
* Validates null operands, invalid units, NaN, infinite values
* Throws `ArithmeticException` for division by zero
* Preserves mathematical properties (non-commutativity, immutability)

---

### Example

**Input:**

```bash
new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES))  
new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES)  
new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET))  
new Quantity<>(10.0, WeightUnit.KILOGRAM).divide(new Quantity<>(5.0, WeightUnit.KILOGRAM))  
```

**Output:**

```bash
Quantity(9.5, FEET)  
Quantity(114.0, INCHES)  
1.0  
2.0  
```

---

### Test Coverage

The following test scenarios are covered:

* Subtraction with same units and cross units
* Explicit target unit subtraction
* Subtraction resulting in negative and zero values
* Division with same units and cross units
* Division resulting in ratios > 1, < 1, = 1
* Division by zero handling
* Cross-category arithmetic prevention
* Null operand and null target unit validation
* Precision handling and rounding consistency
* Immutability verification
* Integration with addition (A + B - B ≈ A)
* Method chaining support
* Non-commutativity validation

---

### Architectural Impact

* No breaking changes to existing API
* All UC1–UC11 tests pass without modification
* Design remains scalable for future arithmetic operations
* Reinforces SOLID principles and generic architecture extensibility

---
