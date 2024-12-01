package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.service.AvailabilityService;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatsController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final AvailabilityService availabilityService;

    @GetMapping
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("productsAmount", productService.getTotalAmount());
        stats.put("categoriesAmount", categoryService.getTotalCategories());
        stats.put("availabilitiesAmount", availabilityService.getTotalAvailabilities());
        return stats;
    }

}
