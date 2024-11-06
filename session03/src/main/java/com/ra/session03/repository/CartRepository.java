package com.ra.session03.repository;

import com.ra.session03.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProductProductIdAndCustomerCustomerId(Long productId, Long customerId);

}
