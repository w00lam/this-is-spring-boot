package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryDslRepository {
    List<Product> findByTitleContaining(String title);

    List<Product> findByDescriptionContaining(String description);
}
