package com.ra.session03.controller;



import com.ra.session03.model.dto.product.ProductDto;
import com.ra.session03.model.entity.Product;
import com.ra.session03.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    private ResponseEntity<Page<Product>> index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) String keyword) {
        Page<Product> products = productService.findAll(page, size, keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }




    @PostMapping
    private ResponseEntity<Product> create(@ModelAttribute ProductDto productDto) {
        try {
            // Gọi ProductService để lưu trữ sản phẩm
            Product product = productService.save(productDto);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            Map<String,String> error = new HashMap<>();
            error.put("mess","không tìm thấy");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> update(@PathVariable Long id, @ModelAttribute ProductDto productDto) {
          Product product = productService.update(id, productDto);
          if (product != null) {
              return new ResponseEntity<>(product, HttpStatus.OK);
          } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Product> delete(@PathVariable Long id) {
    productService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
