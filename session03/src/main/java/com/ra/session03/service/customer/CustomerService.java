package com.ra.session03.service.customer;

import com.ra.session03.model.dto.customer.request.CustomerRequestDTO;
import com.ra.session03.model.dto.customer.respone.CustomerResponeseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerResponeseDTO> findAll();
    CustomerResponeseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponeseDTO findCustomerById(Long id);
}
