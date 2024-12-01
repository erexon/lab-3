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


    public Availability create(String name) {
        if (availabilityRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Availability already exists");
        }
        // Создание объекта Availability, а не Category
        Availability availability = new Availability();
        availability.setName(name);
        return availabilityRepository.save(availability); // Сохранение корректного объекта
    }

    public Availability update(Long id, String name) {
        // Проверка, существует ли объект Availability с таким ID
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found with id: " + id));
        // Обновление имени объекта
        availability.setName(name);
        return availabilityRepository.save(availability);
    }

    public void delete(Long id) {
        // Проверка, существует ли объект Availability с таким ID
        if (!availabilityRepository.existsById(id)) {
            throw new RuntimeException("Availability not found with id: " + id);
        }
        availabilityRepository.deleteById(id);
    }

    public long getTotalAvailabilities() {
        return availabilityRepository.count();
    }

}
