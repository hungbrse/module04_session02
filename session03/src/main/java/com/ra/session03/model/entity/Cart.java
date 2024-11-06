package com.ra.session03.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customers")
    private Customer customer;
    private long quantity;

    @Column(name = "added_date")
    private LocalDate addedDate; // Ngày sản phẩm được thêm vào giỏ hàng

    @Column(name = "status")
    private String status;
}
