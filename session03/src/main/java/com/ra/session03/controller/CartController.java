package com.ra.session03.controller;

import com.ra.session03.model.dto.cart.request.CartRequestDTO;
import com.ra.session03.model.entity.Cart;
import com.ra.session03.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        List<Cart> carts = cartService.findAll();

        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(carts,HttpStatus.OK);
        }
        }

        @PostMapping
    public ResponseEntity<Cart> create(@RequestBody CartRequestDTO cartRequestDTO) {
        Cart cart = cartService.save(cartRequestDTO);
        return new ResponseEntity<>(cart,HttpStatus.CREATED);
        }
    }

