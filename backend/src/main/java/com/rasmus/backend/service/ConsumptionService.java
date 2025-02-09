package com.rasmus.backend.service;

import com.rasmus.backend.model.Consumption;
import com.rasmus.backend.repository.ConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsumptionService {

    private final ConsumptionRepository consumptionRepository;

    public List<Consumption> getMonthlyConsumption(Long meteringId) {
        return consumptionRepository.findByMeteringPoint_MeteringId(meteringId);
    }
    public Map<Month, BigDecimal> getMonthlyAverageConsumptionWithCost(Long meteringId) {
        List<Consumption> consumptions = consumptionRepository.findByMeteringPoint_MeteringId(meteringId);

        return consumptions.stream()
            .collect(Collectors.groupingBy(
                c -> c.getTime().getMonth(),
                Collectors.mapping(Consumption::getAmount, 
                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                )
            ));
    }
}
