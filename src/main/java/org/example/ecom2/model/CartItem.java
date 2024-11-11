//package org.example.ecom2.model;
//
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter@Entity
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Cart cart;
//
//    @ManyToOne
//    private Item item;
//
//    private int quantity;
//
//    public double getTotalPrice() {
//        return item.getPrice() * quantity;
//    }
//
//    // Getters and Setters
//}
