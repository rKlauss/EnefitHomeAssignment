package com.rasmus.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rasmus.backend.dto.MarketPriceDTO;
import com.rasmus.backend.service.MarketPriceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/market")
public class MarketPriceController {

    private final MarketPriceService marketPriceService;

    @GetMapping("/prices")
    public ResponseEntity<List<MarketPriceDTO>> getMarketPrices() {
        List<MarketPriceDTO> marketPrices = marketPriceService.fetchMarketPrices();
        return ResponseEntity.ok(marketPrices);
    }
    
}