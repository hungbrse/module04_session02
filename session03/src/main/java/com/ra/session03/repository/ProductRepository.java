package com.ra.session03.repository;

import com.ra.session03.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    Page<Product> findByProductNameContaining(String keyword, Pageable pageable);
}
