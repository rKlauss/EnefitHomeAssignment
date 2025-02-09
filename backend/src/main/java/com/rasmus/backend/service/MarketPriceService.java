package com.rasmus.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasmus.backend.dto.MarketPriceDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;  
import org.springframework.http.ResponseEntity;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MarketPriceService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MarketPriceService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<MarketPriceDTO> fetchMarketPrices() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String startDateTime = OffsetDateTime.now(ZoneOffset.UTC).minusDays(1).format(formatter);
        String endDateTime = OffsetDateTime.now(ZoneOffset.UTC).format(formatter);

        String url = String.format(
            "https://estfeed.elering.ee/api/public/v1/energy-price/electricity?startDateTime=%s&endDateTime=%s",
            startDateTime, endDateTime
        );

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String responseBody = responseEntity.getBody();

            return objectMapper.readValue(responseBody, new TypeReference<List<MarketPriceDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch market prices: " + e.getMessage(), e);
        }
    }
}
