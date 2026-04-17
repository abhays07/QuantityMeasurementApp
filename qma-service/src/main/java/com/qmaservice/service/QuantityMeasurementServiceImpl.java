package com.qmaservice.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.qmaservice.core.*;
import com.qmaservice.dto.*;
import com.qmaservice.model.*;
import com.qmaservice.repository.*;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final QuantityMeasurementRepository repository;

	@Autowired
	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	/**
	 * SECURITY: Extracts numeric User ID from JWT subject.
	 */
	public Long getAuthenticatedUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
			return null;
		}
		try {
			// This will now receive "1", "2", etc. from the updated JwtUtil
			return Long.parseLong(auth.getName());
		} catch (NumberFormatException e) {
			System.err.println("LOGICAL ERROR: Token subject is not a number: " + auth.getName());
			return null;
		}
	}

	/**
	 * PERSISTENCE: Saves operation to MySQL. 
	 * Now includes debug logs to verify the fix.
	 */
	private void saveToHistory(Long userId, String op, String op1, String op2, String res) {
		System.out.println("DEBUG: Attempting to save for User ID: " + userId);
		
		if (userId != null) {
			QuantityMeasurementEntity entity = new QuantityMeasurementEntity(userId, op, op1, op2, res);
			QuantityMeasurementEntity saved = repository.save(entity);
			System.out.println("SUCCESS: Operation saved to DB with ID: " + saved.getId());
		} else {
			System.err.println("FAILED: userId is NULL. Record not saved.");
		}
	}

	private Quantity<?> createQuantity(QuantityDTO dto) {
		String unitStr = dto.getUnit().toUpperCase();
		IMeasurable unit;
		if (isUnitOf(LengthUnit.class, unitStr)) unit = LengthUnit.valueOf(unitStr);
		else if (isUnitOf(WeightUnit.class, unitStr)) unit = WeightUnit.valueOf(unitStr);
		else if (isUnitOf(VolumeUnit.class, unitStr)) unit = VolumeUnit.valueOf(unitStr);
		else if (isUnitOf(TemperatureUnit.class, unitStr)) unit = TemperatureUnit.valueOf(unitStr);
		else throw new IllegalArgumentException("Unsupported Unit: " + dto.getUnit());
		return new Quantity<>(dto.getValue(), unit);
	}

	private boolean isUnitOf(Class<? extends Enum<?>> enumClass, String value) {
		for (Enum<?> e : enumClass.getEnumConstants()) {
			if (e.name().equals(value)) return true;
		}
		return false;
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {
		return createQuantity(q1).equals(createQuantity(q2));
	}

	@Override
	@Cacheable(value = "calculations", key = "{#input.value, #input.unit, #targetUnit}")
	public QuantityDTO convert(QuantityDTO input, String targetUnit) {
		Quantity<?> quantity = createQuantity(input);
		Quantity result = ((Quantity) quantity).convertTo(createQuantity(new QuantityDTO(0, targetUnit)).getUnit());
		return new QuantityDTO(result.getValue(), result.getUnit().toString());
	}

	@Override
	@CacheEvict(value = "history", key = "T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().name")
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
		Quantity quantity1 = createQuantity(q1);
		Quantity quantity2 = createQuantity(q2);
		Quantity result = (Quantity) ((Quantity) quantity1).add((Quantity) quantity2);
		saveToHistory(getAuthenticatedUserId(), "ADD", quantity1.toString(), quantity2.toString(), result.toString());
		return new QuantityDTO(result.getValue(), result.getUnit().toString());
	}

	@Override
	@CacheEvict(value = "history", key = "T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().name")
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
		Quantity quantity1 = createQuantity(q1);
		Quantity quantity2 = createQuantity(q2);
		Quantity result = (Quantity) ((Quantity) quantity1).subtract((Quantity) quantity2);
		saveToHistory(getAuthenticatedUserId(), "SUBTRACT", quantity1.toString(), quantity2.toString(), result.toString());
		return new QuantityDTO(result.getValue(), result.getUnit().toString());
	}

	@Override
	@CacheEvict(value = "history", key = "T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().name")
	public double divide(QuantityDTO q1, QuantityDTO q2) {
		Quantity quantity1 = createQuantity(q1);
		Quantity quantity2 = createQuantity(q2);
		double result = (double) ((Quantity) quantity1).divide((Quantity) quantity2);
		saveToHistory(getAuthenticatedUserId(), "DIVIDE", quantity1.toString(), quantity2.toString(), String.valueOf(result));
		return result;
	}

	@Override
	@CircuitBreaker(name = "redisService", fallbackMethod = "fallbackGetAllMeasurements")
	@Cacheable(value = "history", key = "T(org.springframework.security.core.context.SecurityContextHolder).getContext().getAuthentication().name")
	public List<QuantityMeasurementEntity> getAllMeasurements() {
		return fetchHistoryFromDb();
	}

	public List<QuantityMeasurementEntity> fallbackGetAllMeasurements(Throwable t) {
		System.err.println("--- CIRCUIT BREAKER: REDIS DOWN. FETCHING FROM DB ---");
		return fetchHistoryFromDb();
	}

	private List<QuantityMeasurementEntity> fetchHistoryFromDb() {
		Long userId = getAuthenticatedUserId();
		if (userId == null) return new ArrayList<>();
		return repository.findByUserIdOrderByCreatedAtDesc(userId);
	}
}