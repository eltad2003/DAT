package org.example.ecom2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private Shipment shipment;

    @OneToOne
    private Payment payment;

    private String status;

    private Date orderDate = new Date(); // Thời điểm đặt hàng

}
