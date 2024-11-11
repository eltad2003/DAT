package org.example.ecom2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Cart cart;

    @OneToOne
    private Shipment shipment;

    @OneToOne
    private Payment payment;

    private String status;
}
