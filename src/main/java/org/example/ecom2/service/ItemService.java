package org.example.ecom2.service;

import org.example.ecom2.model.Item;
import org.example.ecom2.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> searchItem(String keyword) {
        return itemRepository.findByNameContainingIgnoreCase(keyword);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }
}
