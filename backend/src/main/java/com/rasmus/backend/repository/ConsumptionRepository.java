package com.rasmus.backend.repository;

import com.rasmus.backend.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    List<Consumption> findByMeteringPoint_MeteringId(Long meteringId);
}
