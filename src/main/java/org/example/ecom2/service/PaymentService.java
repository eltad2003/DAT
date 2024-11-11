package org.example.ecom2.service;

import org.example.ecom2.model.Payment;
import org.example.ecom2.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

}
