package com.ra.session03.service.cart;

import com.ra.session03.model.dto.cart.request.CartRequestDTO;
import com.ra.session03.model.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll();
    Cart save(CartRequestDTO cartRequestDTO);
}
