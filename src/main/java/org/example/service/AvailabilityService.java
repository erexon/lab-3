package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Availability;
import org.example.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;

    public List<Availability> readAll() {
        return availabilityRepository.findAll();
    }

    public Availability readById(Long id) {
        return availabilityRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Availability not found - " + id));
    }

}
