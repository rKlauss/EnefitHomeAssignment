package com.rasmus.backend.service;

import com.rasmus.backend.model.Customer;
import com.rasmus.backend.model.MeteringPoint;
import com.rasmus.backend.repository.CustomerRepository;
import com.rasmus.backend.repository.MeteringPointRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MeteringPointRepository meteringPointRepository;

    public boolean hasAccessToMeteringPoint(String username, Long meteringId) {
        Customer customer = customerRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Customer not found with username: " + username));

        MeteringPoint meteringPoint = meteringPointRepository.findById(meteringId)
            .orElseThrow(() -> new RuntimeException("Metering point not found with ID: " + meteringId));

        return meteringPoint.getCustomer().getCustomerId().equals(customer.getCustomerId());
    }
}
