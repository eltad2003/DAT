package org.example.ecom2.repository;

import org.example.ecom2.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

