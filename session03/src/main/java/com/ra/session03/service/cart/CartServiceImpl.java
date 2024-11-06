package com.ra.session03.service.cart;

import com.ra.session03.model.dto.cart.request.CartRequestDTO;
import com.ra.session03.model.entity.Cart;
import com.ra.session03.model.entity.Customer;
import com.ra.session03.model.entity.Product;
import com.ra.session03.repository.CartRepository;
import com.ra.session03.repository.CustomerRepository;
import com.ra.session03.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {


    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(CartRequestDTO cartRequestDTO) {


        Optional<Product> product = productRepository.findById(cartRequestDTO.getProductId());
        Optional<Customer> customer = customerRepository.findById(cartRequestDTO.getCustomerId());

        // Kiểm tra nếu product hoặc customer không tồn tại
        if (product.isEmpty() || customer.isEmpty()) {
            throw new RuntimeException("Product or Customer not found.");
        }

        // Kiểm tra nếu sản phẩm đã có trong giỏ hàng của khách hàng này
        Optional<Cart> existingCart = cartRepository.findByProductProductIdAndCustomerCustomerId(cartRequestDTO.getProductId(), cartRequestDTO.getCustomerId());
        if (existingCart.isPresent()) {
            // Cập nhật số lượng sản phẩm hiện tại
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + cartRequestDTO.getQuantity());
            return cartRepository.save(cart);
        }

        // Nếu chưa có, tiến hành thêm mới sản phẩm vào giỏ hàng
        Cart cart = Cart.builder()
                .product(product.get())
                .customer(customer.get())
                .quantity(cartRequestDTO.getQuantity()) // Sử dụng quantity từ request
                .addedDate(cartRequestDTO.getAddedDate())
                .status(cartRequestDTO.getStatus())
                .build();

        return cartRepository.save(cart);
    }
}
