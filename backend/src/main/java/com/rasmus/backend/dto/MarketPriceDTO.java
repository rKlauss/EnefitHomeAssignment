package com.rasmus.backend.dto;

import java.time.ZonedDateTime;
import lombok.Data;
@Data
public class MarketPriceDTO {
    private double centsPerKwh;
    private double centsPerKwhWithVat;
    private double eurPerMwh;
    private double eurPerMwhWithVat;
    private ZonedDateTime fromDateTime;
    private ZonedDateTime toDateTime;
}
