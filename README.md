# Quantity Measurement App

**Branch:** `feature/UC11-VolumeMeasurement`
**Code Link:** [https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC11-VolumeMeasurement](https://github.com/abhays07/QuantityMeasurementApp/blob/feature/UC11-VolumeMeasurement)

---

## UC11: Volume Measurement Equality, Conversion, and Addition

### Overview

This use case extends the generic `Quantity<U>` implementation to support volume measurements using a new `VolumeUnit` enum implementing the `IMeasurable` interface. It enables equality comparison, unit conversion, and addition operations for volume units such as litre, millilitre, and gallon. The implementation validates the scalability of the generic architecture by integrating a new measurement category without modifying existing core classes.

---

### Features Implemented

* Added `VolumeUnit` enum implementing `IMeasurable` with litre, millilitre, and gallon support
* Enabled generic `Quantity<U>` to handle volume equality, conversion, and addition
* Ensured seamless integration without modifying existing generic Quantity class
* Maintained strict type safety between volume, length, and weight categories
* Validated architectural scalability using generic design pattern

---

### Preconditions

* The `QuantityMeasurementApp` class is initialized
* Volume measurements are provided using valid `VolumeUnit` enum constants

---

### Functionality

* Compares volume quantities using base unit normalization (litre)
* Converts volume values between litre, millilitre, and gallon
* Adds volume quantities with implicit and explicit target unit support
* Ensures generic Quantity class handles volume without specialized implementation
* Prevents invalid cross-category comparisons using generic type safety

---

### Example

**Input:**

```bash
new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE))  
new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE)  
new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE)  
new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE)  
```

**Output:**

```bash
true  
Quantity(1000.0, MILLILITRE)  
Quantity(2.0, LITRE)  
Quantity(3.78541, LITRE)  
```

---

### Test Coverage

The following test scenarios are covered:

* Equality comparison across litre, millilitre, and gallon units
* Unit conversion between all supported volume units
* Addition operations using generic Quantity class with volume units
* Prevention of cross-category comparisons between volume, length, and weight
* Validation of null units, invalid inputs, and floating-point precision handling

---
