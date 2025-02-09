package com.rasmus.backend.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "consumption")
public class Consumption {
    @Id
    @Column(name = "consumption_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long consumptionId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "metering_point_id") 
    private MeteringPoint meteringPoint;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "amount_unit")
    private String unit;

    @Column(name = "consumption_time")
    private ZonedDateTime time;

    public Consumption(MeteringPoint  meteringPoint, BigDecimal amount, String unit, ZonedDateTime time) {
        this.meteringPoint = meteringPoint;
        this.amount = amount;
        this.unit = unit;
        this.time = time;
    }

}
