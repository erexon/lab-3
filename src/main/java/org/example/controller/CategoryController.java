package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entity.Category;
import org.example.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> readAll() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }


    @PostMapping
    public Category createCategory(@RequestBody Map<String, String> request) {
        String name = request.get("name"); // Извлекаем значение имени из JSON
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String name = request.get("name"); // Извлекаем значение имени из JSON
        return categoryService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
