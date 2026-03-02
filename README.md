# Quantity Measurement App

**Branch:** `feature/UC14-TemperatureMeasurement`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement](https://github.com/abhays07/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement)

---

## UC14: Temperature Measurement with Selective Arithmetic Support and IMeasurable Refactoring

### Overview

UC14 extends the generic `Quantity<U>` architecture to support **temperature measurements** alongside length, weight, and volume.

Unlike other categories, temperature has **operational constraints**:

* Supports **equality comparison** and **unit conversion**
* Does **not** meaningfully support addition, subtraction (absolute values), or division
* Requires **non-linear conversion formulas**

To address this, UC14 refactors the `IMeasurable` interface to introduce **optional arithmetic support** using:

* Default methods
* A `SupportsArithmetic` functional interface
* Lambda expressions for capability declaration

This enhancement preserves backward compatibility while enforcing **Interface Segregation Principle (ISP)** and maintaining type safety.

---

### Features Implemented

* Added `TemperatureUnit` enum implementing `IMeasurable`

  * Supports CELSIUS, FAHRENHEIT, and KELVIN
* Refactored `IMeasurable` to include:

  * `supportsArithmetic()` default method
  * `validateOperationSupport(String operation)` default method
* Introduced `SupportsArithmetic` functional interface
* Used lambda expressions to define operation capability
* Overrode arithmetic validation in `TemperatureUnit`
* Enhanced `Quantity<U>` to validate operation support before execution
* Ensured cross-category comparisons remain prohibited
* Preserved full backward compatibility (UC1–UC13 tests pass unchanged)

---

### Preconditions

* All UC1–UC13 functionality is operational
* Centralized arithmetic logic from UC13 is in place
* `LengthUnit`, `WeightUnit`, and `VolumeUnit` remain unchanged
* `IMeasurable` is refactored with default methods
* `Quantity<U>` validates operation support before arithmetic execution
* Temperature conversion formulas follow scientific standards

---

### Functionality

* Supports temperature equality across different units
* Supports accurate non-linear conversion between:

  * Celsius
  * Fahrenheit
  * Kelvin
* Prevents unsupported arithmetic operations
* Throws `UnsupportedOperationException` for:

  * add()
  * subtract()
  * divide()
* Maintains compile-time generic type safety
* Maintains runtime category validation
* Preserves immutability and rounding consistency

---

### Example

**Input:**

```bash
new Quantity<>(0.0, TemperatureUnit.CELSIUS)
    .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))

new Quantity<>(100.0, TemperatureUnit.CELSIUS)
    .convertTo(TemperatureUnit.FAHRENHEIT)

new Quantity<>(100.0, TemperatureUnit.CELSIUS)
    .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS))
```

**Output:**

```bash
true
Quantity(212.0, FAHRENHEIT)
Throws UnsupportedOperationException
```

---

### Supported Temperature Equality Cases

* 0°C = 32°F
* 100°C = 212°F
* 0°C = 273.15 K
* -40°C = -40°F
* 100°C = 373.15 K

---

### Unsupported Operations (By Design)

```bash
new Quantity<>(100.0, CELSIUS).add(...)
new Quantity<>(100.0, CELSIUS).subtract(...)
new Quantity<>(100.0, CELSIUS).divide(...)
```

All throw:

```bash
UnsupportedOperationException:
"Temperature does not support arithmetic operations."
```

---

### Architectural Improvements

* Refactored `IMeasurable` using default methods
* Introduced capability-based design
* Enforced Interface Segregation Principle (ISP)
* Eliminated dummy implementations for unsupported operations
* Preserved DRY principle
* Centralized operation validation strategy
* Improved API clarity and error messaging
* Scalable to future constrained categories

---

### Design Principles Applied

* Interface Segregation Principle (ISP)
* Open/Closed Principle
* DRY Principle
* Capability-Based Design
* Enum-Based Polymorphism
* Functional Interfaces
* Lambda Expressions
* Backward-Compatible Refactoring

---

### Test Coverage

The following scenarios are covered:

* Celsius-to-Celsius equality
* Fahrenheit-to-Fahrenheit equality
* Kelvin-to-Kelvin equality
* Cross-unit equality validation
* Non-linear conversion accuracy
* Absolute zero handling (-273.15°C)
* Equal-point validation (-40°C = -40°F)
* Round-trip conversion preservation
* Unsupported operation handling
* Error message clarity validation
* Cross-category comparison prevention
* Null operand validation
* Epsilon-based precision validation
* Operation capability checks
* Backward compatibility with UC1–UC13
* Integration with generic `Quantity<U>`

All previous unit tests pass without modification.

---

### Architectural Impact

* No breaking changes
* Existing units inherit default arithmetic support
* Temperature explicitly disables arithmetic
* Generic `Quantity<U>` design remains intact
* System now supports categories with selective capabilities
* Foundation prepared for future constrained measurement types

---
