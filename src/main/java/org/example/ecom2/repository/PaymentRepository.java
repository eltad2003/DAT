package org.example.ecom2.repository;

import org.example.ecom2.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {}
