package org.example.ecom2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Item item;


}
