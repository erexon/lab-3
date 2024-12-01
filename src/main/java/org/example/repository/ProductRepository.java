package org.example.repository;

import org.example.entity.Category;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Category> findByName(String name);
    List<Product> findByCategoryId(Long id);
    List<Product> findByAvailabilityId(Long id);
    @Query("SELECT SUM(p.amount) FROM Product p")
    Long sumAmount();
}
