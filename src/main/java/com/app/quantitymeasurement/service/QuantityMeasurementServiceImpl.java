package com.app.quantitymeasurement.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.core.*;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.UserRepository;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // Helper: Get Authenticated User from Security Context
    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }
        // Name usually contains the email in a JWT setup
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }

    // Helper: Persist History ONLY if user is logged in
    private void saveToHistory(String op, String op1, String op2, String res) {
        User user = getAuthenticatedUser();
        if (user != null) {
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
            entity.setUser(user);
            entity.setOperation(op);
            entity.setOperand1(op1);
            entity.setOperand2(op2);
            entity.setResult(res);
            repository.save(entity);
        }
    }

    private Quantity<?> createQuantity(QuantityDTO dto) {
        try { return new Quantity<>(dto.getValue(), LengthUnit.valueOf(dto.getUnit())); } catch (Exception ignored) {}
        try { return new Quantity<>(dto.getValue(), WeightUnit.valueOf(dto.getUnit())); } catch (Exception ignored) {}
        try { return new Quantity<>(dto.getValue(), VolumeUnit.valueOf(dto.getUnit())); } catch (Exception ignored) {}
        try { return new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(dto.getUnit())); } catch (Exception ignored) {}
        throw new IllegalArgumentException("Unsupported Unit: " + dto.getUnit());
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return createQuantity(q1).equals(createQuantity(q2));
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {
        Quantity<?> quantity = createQuantity(input);
        Object unitType = quantity.getUnit();

        QuantityDTO resultDTO;
        if (unitType instanceof LengthUnit) {
            Quantity<LengthUnit> res = ((Quantity<LengthUnit>) quantity).convertTo(LengthUnit.valueOf(targetUnit));
            resultDTO = new QuantityDTO(res.getValue(), res.getUnit().name());
        } else if (unitType instanceof WeightUnit) {
            Quantity<WeightUnit> res = ((Quantity<WeightUnit>) quantity).convertTo(WeightUnit.valueOf(targetUnit));
            resultDTO = new QuantityDTO(res.getValue(), res.getUnit().name());
        } else if (unitType instanceof VolumeUnit) {
            Quantity<VolumeUnit> res = ((Quantity<VolumeUnit>) quantity).convertTo(VolumeUnit.valueOf(targetUnit));
            resultDTO = new QuantityDTO(res.getValue(), res.getUnit().name());
        } else if (unitType instanceof TemperatureUnit) {
            Quantity<TemperatureUnit> res = ((Quantity<TemperatureUnit>) quantity).convertTo(TemperatureUnit.valueOf(targetUnit));
            resultDTO = new QuantityDTO(res.getValue(), res.getUnit().name());
        } else {
            throw new IllegalArgumentException("Conversion Logic failed for Unit Type");
        }
        return resultDTO;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);
        Quantity<?> result = ((Quantity) quantity1).add((Quantity) quantity2);

        saveToHistory("ADD", quantity1.toString(), quantity2.toString(), result.toString());
        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);
        Quantity<?> result = ((Quantity) quantity1).subtract((Quantity) quantity2);

        saveToHistory("SUBTRACT", quantity1.toString(), quantity2.toString(), result.toString());
        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        Quantity<?> quantity1 = createQuantity(q1);
        Quantity<?> quantity2 = createQuantity(q2);
        double result = ((Quantity) quantity1).divide((Quantity) quantity2);

        saveToHistory("DIVIDE", quantity1.toString(), quantity2.toString(), String.valueOf(result));
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        User user = getAuthenticatedUser();
        if (user == null) return new ArrayList<>();
        return repository.findByUserOrderByCreatedAtDesc(user);
    }
}