package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Availability;
import org.example.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/availabilities")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping
    public ResponseEntity<List<Availability>> readAll() {
        return new ResponseEntity<>(availabilityService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public Availability createAvailability(@RequestBody Map<String, String> request) {
        String name = request.get("name"); // Извлекаем значение имени из JSON
        return availabilityService.create(name);
    }

    @PutMapping("/{id}")
    public Availability updateAvailability(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String name = request.get("name"); // Извлекаем значение имени из JSON
        return availabilityService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteAvailability(@PathVariable Long id) {
        availabilityService.delete(id);

    }
}
