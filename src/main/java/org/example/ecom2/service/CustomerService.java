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
    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username).get();
    }

    public String registerCustomer(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
            return "Customer already exists";
        }
        customerRepository.save(customer);
        return "successfully";
    }

}
