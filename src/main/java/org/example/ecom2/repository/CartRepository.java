package org.example.ecom2.repository;

import org.example.ecom2.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartRepository extends JpaRepository<Cart, Long> {
}
