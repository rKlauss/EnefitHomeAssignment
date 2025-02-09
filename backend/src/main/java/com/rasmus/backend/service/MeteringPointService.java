package com.rasmus.backend.service;

import com.rasmus.backend.model.MeteringPoint;
import com.rasmus.backend.repository.MeteringPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeteringPointService {

    private final MeteringPointRepository meteringPointRepository;

    public List<MeteringPoint> getMeteringPointsByUsername(String username) {
        return meteringPointRepository.findByCustomerUsername(username);
    }
}


