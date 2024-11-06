package com.ra.session03.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(name = "email",length = 100,unique = true,nullable = false)
    private String email;
    @Column(name = "full_name",length = 32,nullable = false)
    private String fullName;
    @Column(name = "password",length = 32,nullable = false)
    private String password;
    @Column(name = "birthDay",nullable = false)
    private LocalDate birthDay;
    @Column(name = "status",nullable = false)
    private Boolean status;
}
