package org.example.ecom2.service;

import org.example.ecom2.model.Customer;
import org.example.ecom2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
