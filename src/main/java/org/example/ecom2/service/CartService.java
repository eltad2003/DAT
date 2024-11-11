package org.example.ecom2.service;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Item;
import org.example.ecom2.repository.CartRepository;
import org.example.ecom2.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Cart getDefaultCart() {
        // Giả sử bạn có một giỏ hàng mặc định với ID là 1
        return cartRepository.findById(1L).orElse(null);
    }

   public Cart addCart(Long itemId) {
       Cart cart = getDefaultCart();
       Optional<Item> itemOptional = itemRepository.findById(itemId);
       if (itemOptional.isPresent()) {
           Item item = itemOptional.get();
           cart.getItems().add(item);
           return cartRepository.save(cart);
       }
       return cart;
   }

   public Cart removeCart(Long itemId) {
        Cart cart = getDefaultCart();
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.getItems().remove(item);
            return cartRepository.save(cart);
        }
        return cart;
   }



}

