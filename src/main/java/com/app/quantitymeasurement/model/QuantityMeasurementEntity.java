package com.app.quantitymeasurement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantity_measurements")
@Data  					// 1. Generates Getters, Setters, toString, equals, and hashCode
@AllArgsConstructor		// 2. Generates a constructor with all fields
@NoArgsConstructor		// 3. Generates the mandatory No-Argument constructor for JPA
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	String operation;
	private String operand1;
	private String operand2;
	private String result;
	private String errorMessage;

}