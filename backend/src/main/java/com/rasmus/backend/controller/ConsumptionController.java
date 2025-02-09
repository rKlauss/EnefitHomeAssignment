package com.rasmus.backend.controller;

import com.rasmus.backend.model.Consumption;
import com.rasmus.backend.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consumptions")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    @GetMapping("/all/{meteringId}")
    public ResponseEntity<List<Consumption>> getAllConsumptions(@PathVariable Long meteringId) {
        List<Consumption> consumptions = consumptionService.getMonthlyConsumption(meteringId);
        return ResponseEntity.ok(consumptions);
    }

    @GetMapping("/monthly/{meteringId}")
    public ResponseEntity<Map<Month, BigDecimal>> getMonthlyAverageConsumption(@PathVariable Long meteringId) {
        Map<Month, BigDecimal> averageConsumption = consumptionService.getMonthlyAverageConsumptionWithCost(meteringId);
        return ResponseEntity.ok(averageConsumption);
    }
}


