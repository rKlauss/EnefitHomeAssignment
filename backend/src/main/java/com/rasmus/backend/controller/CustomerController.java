package com.rasmus.backend.controller;

import com.rasmus.backend.model.Customer;
import com.rasmus.backend.model.MeteringPoint;
import com.rasmus.backend.repository.CustomerRepository;
import com.rasmus.backend.service.MeteringPointService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final MeteringPointService meteringPointService;  

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Customer loginRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(loginRequest.getUsername());

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(customer.getUsername(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authToken);

                List<MeteringPoint> meteringPoints = meteringPointService.getMeteringPointsByUsername(customer.getUsername());

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("customerId", customer.getCustomerId());
                response.put("meteringPoints", meteringPoints);

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<Long> getCustomerIdByUsername(@PathVariable String username) {
        Customer customer = customerRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return ResponseEntity.ok(customer.getCustomerId());
    }
}
