package com.ra.session03.service.customer;

import com.ra.session03.model.dto.customer.request.CustomerRequestDTO;
import com.ra.session03.model.dto.customer.respone.CustomerResponeseDTO;
import com.ra.session03.model.entity.Customer;
import com.ra.session03.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponeseDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponeseDTO> customerResponeseDTOS ;
        customerResponeseDTOS = customers.stream().map(customer ->
                CustomerResponeseDTO.builder()
                        .customerId(customer.getCustomerId())
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .birthDate(customer.getBirthDay())
                        .status(customer.getStatus())
                        .build() // Thêm `.build()` ở cuối để hoàn thành builder
        ).toList();

        return customerResponeseDTOS;
    }

    @Override
    public CustomerResponeseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .fullName(customerRequestDTO.getFullName())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .birthDay(customerRequestDTO.getBirthDate())
                .status(customerRequestDTO.getStatus())
                .build();
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponeseDTO.builder()
                .customerId(savedCustomer.getCustomerId())
                .fullName(savedCustomer.getFullName())
                .email(savedCustomer.getEmail())
                .birthDate(savedCustomer.getBirthDay())
                .status(savedCustomer.getStatus())
                .build();
    }

    @Override
    public CustomerResponeseDTO findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            CustomerResponeseDTO customerResponeseDTO = new CustomerResponeseDTO();
            customerResponeseDTO.setCustomerId(customer.getCustomerId());
            customerResponeseDTO.setFullName(customer.getFullName());
            customerResponeseDTO.setEmail(customer.getEmail());
            customerResponeseDTO.setStatus(customer.getStatus());
            customerResponeseDTO.setBirthDate(customer.getBirthDay());
            return customerResponeseDTO;
        }
        return null;
    }
}
