package com.rasmus.backend.repository;

import com.rasmus.backend.model.MeteringPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeteringPointRepository extends JpaRepository<MeteringPoint, Long> {
    List<MeteringPoint> findByCustomerUsername(String username);
    List<MeteringPoint> findByCustomerCustomerId(Long customerId);
}


