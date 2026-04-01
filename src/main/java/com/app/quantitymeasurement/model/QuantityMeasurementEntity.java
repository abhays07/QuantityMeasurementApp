package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String errorMessage;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Manual constructor for cleaner service code
    public QuantityMeasurementEntity(User user, String operation, String operand1, String operand2, String result) {
        this.user = user;
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }
}