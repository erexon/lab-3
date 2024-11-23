package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Availability;
import org.example.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/availabilities")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<Availability>> readAll() {
        return new ResponseEntity<>(availabilityService.readAll(), HttpStatus.OK);
    }
}
