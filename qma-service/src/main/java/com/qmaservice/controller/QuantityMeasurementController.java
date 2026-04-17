package com.qmaservice.controller;

import com.qmaservice.model.*;
import com.qmaservice.dto.*;
import com.qmaservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/convert/{targetUnit}")
    public ResponseEntity<QuantityDTO> convert(@RequestBody QuantityDTO input, @PathVariable String targetUnit) {
        return ResponseEntity.ok(service.convert(input, targetUnit));
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(@RequestBody QuantityInputDTO input) {
        // BYPASS: Direct call to service logic to avoid 503 timeouts
        return ResponseEntity.ok(service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody QuantityInputDTO input) {
        return ResponseEntity.ok(service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistory() {
        return ResponseEntity.ok(service.getAllMeasurements());
    }
}