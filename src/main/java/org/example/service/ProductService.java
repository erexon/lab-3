package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.entity.Availability;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AvailabilityService availabilityService;


    public Product create(ProductDTO dto) {
        if (productRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        Product product = Product.builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .category(categoryService.readById(dto.getCategoryId()))
                .availability(availabilityService.readById(dto.getAvailabilityId()))
                .build();
        return productRepository.save(product);
    }

    public List<Product> readByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> readByAvailabilityId(Long id) {
        return productRepository.findByAvailabilityId(id);
    }

    public List<ProductDTO> readAll() {
        return productRepository.findAll().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setAmount(product.getAmount());
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
            dto.setAvailabilityId(product.getAvailability().getId());
            dto.setAvailabilityName(product.getAvailability().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public Product update(ProductDTO dto) {
        Category category = categoryService.readById(dto.getCategoryId());
        Availability availability = availabilityService.readById(dto.getAvailabilityId());

        Product product = productRepository.findById(dto.getId()).orElseThrow(() ->
                new RuntimeException("Product not found"));

        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setCategory(category);
        product.setAvailability(availability);

        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public long getTotalAmount() {
        return productRepository.sumAmount();
    }

}
