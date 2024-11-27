package org.example.ecom2.repository;

import org.example.ecom2.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername (String username);
}

