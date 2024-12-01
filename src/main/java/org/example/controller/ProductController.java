package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO dto) {

        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() {
       return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> readByCategoryId(@PathVariable Long id){
        return new ResponseEntity<>(productService.readByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<List<Product>> readByAvailabilityId(@PathVariable Long id){
        return new ResponseEntity<>(productService.readByAvailabilityId(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody ProductDTO product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}
