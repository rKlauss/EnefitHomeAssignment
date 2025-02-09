package com.rasmus.backend.controller;

import com.rasmus.backend.model.MeteringPoint;
import com.rasmus.backend.service.MeteringPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metering-point")
public class MeteringPointController {

    private final MeteringPointService meteringPointService;

    @GetMapping
    public ResponseEntity<List<MeteringPoint>> getMeteringPointsForCustomer(Authentication authentication) {
        String username = authentication.getName();
        List<MeteringPoint> meteringPoints = meteringPointService.getMeteringPointsByUsername(username);
        return ResponseEntity.ok(meteringPoints);
    }
}
