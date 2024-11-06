package com.ra.session03.controller;

import com.ra.session03.model.dto.customer.request.CustomerRequestDTO;
import com.ra.session03.model.dto.customer.respone.CustomerResponeseDTO;
import com.ra.session03.service.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponeseDTO>> findAll() {
        List<CustomerResponeseDTO> customerResponeseDTOS = customerService.findAll();
        return new ResponseEntity<>(customerResponeseDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CustomerResponeseDTO> create(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
      CustomerResponeseDTO customerResponeseDTO = customerService.saveCustomer(customerRequestDTO);
      return new ResponseEntity<>(customerResponeseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponeseDTO> findById(@PathVariable long id) {
        CustomerResponeseDTO customerResponeseDTO = customerService.findCustomerById(id);
        if (customerResponeseDTO != null) {
            return new ResponseEntity<>(customerResponeseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
