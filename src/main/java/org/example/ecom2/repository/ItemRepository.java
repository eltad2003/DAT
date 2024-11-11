package org.example.ecom2.repository;
import org.example.ecom2.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findById(long id);
    List<Item> findByNameContainingIgnoreCase(String keyword);
}