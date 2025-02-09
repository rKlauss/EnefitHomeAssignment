package com.rasmus.backend.config;

import com.rasmus.backend.model.Consumption;
import com.rasmus.backend.model.Customer;
import com.rasmus.backend.model.MeteringPoint;
import com.rasmus.backend.repository.ConsumptionRepository;
import com.rasmus.backend.repository.CustomerRepository;
import com.rasmus.backend.repository.MeteringPointRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DummyDataConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, MeteringPointRepository meteringPointRepository, ConsumptionRepository consumptionRepository) {
        return args -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            customerRepository.deleteAll();
            meteringPointRepository.deleteAll();
            consumptionRepository.deleteAll();

            // Create customers
            Customer customer1 = new Customer("John", "Doe", "john_doe", passwordEncoder.encode("password123"));
            Customer customer2 = new Customer("Jane", "Smith", "jane_smith", passwordEncoder.encode("securePass456"));
            Customer customer3 = new Customer("Alice", "Brown", "alice_brown", passwordEncoder.encode("pass789"));

            customerRepository.saveAll(List.of(customer1, customer2, customer3));

            // Fetch customers from DB after saving
            customer1 = customerRepository.findByUsername("john_doe").orElseThrow();
            customer2 = customerRepository.findByUsername("jane_smith").orElseThrow();
            customer3 = customerRepository.findByUsername("alice_brown").orElseThrow();

            // Create metering points linked to customers
            List<MeteringPoint> meteringPoints = List.of(
                new MeteringPoint("Jaan Koorti 8, Tallinn", customer1),
                new MeteringPoint("Jaan Koorti 20, Tallinn", customer1),
                new MeteringPoint("Jaan Koorti 2, Tallinn", customer2),
                new MeteringPoint("Jaan Koorti 14, Tallinn", customer3)
            );

            List<Consumption> consumptions = new ArrayList<>();
            for (var mp : meteringPoints) {
                for (int month = 1; month <= 12; month++) {
                    YearMonth yearMonth = YearMonth.of(2024, month);
                    int daysInMonth = yearMonth.lengthOfMonth();
                    for (int day = 1; day <= daysInMonth; day++) {
                        var amount = new DecimalFormat("##0.00").format(Math.random() * 10);
                        consumptions.add(new Consumption(mp,
                        new BigDecimal(amount), "Kwh", ZonedDateTime.of(2024, month, day, 0, 0, 0, 0, ZoneId.systemDefault())));
                    }
                }
            }
            meteringPointRepository.saveAll(meteringPoints);
            consumptionRepository.saveAll(consumptions);
            // System.out.println(consumptions);

            Map<Integer, BigDecimal> consumptionsMap = new HashMap<>();

            for (Consumption consumption : consumptions) {
                int month = consumption.getTime().getMonthValue();
                consumptionsMap.put(month, consumption.getAmount());
            }

            // Print the map in the desired format
            // System.out.println("{");
            // for (Map.Entry<Integer, BigDecimal> entry : consumptionsMap.entrySet()) {
            //     System.out.println("    \"" + entry.getKey() + "\": " + entry.getValue() + ",");
            // }
            // System.out.println("}");
            // meteringPointRepository.saveAll(meteringPoints).forEach(mp -> System.out.println("Metering Point ID: " + mp.getMeteringId()));
        };
    }
}