package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.entity.Availability;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AvailabilityService availabilityService;


    public Product create(ProductDTO dto) {
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

   public List<Product> readAll() {
        return productRepository.findAll();
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

}
