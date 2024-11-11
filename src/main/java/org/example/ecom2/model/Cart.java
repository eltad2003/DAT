package org.example.ecom2.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<Item> items;

    private double totalPrice;

    public double getTotalPrice() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
  


}
