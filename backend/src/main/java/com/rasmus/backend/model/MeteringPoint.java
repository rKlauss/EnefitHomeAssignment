package com.rasmus.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "metering_points")
public class MeteringPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metering_point_id")
    private Long meteringId;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public MeteringPoint(String address, Customer customer) {
        this.address = address;
        this.customer = customer;
    }
}