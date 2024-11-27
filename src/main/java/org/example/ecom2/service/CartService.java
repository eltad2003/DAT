package org.example.ecom2.service;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Customer;
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

    public Cart getDefaultCartForCustomer(Customer customer) {
       Cart cart = cartRepository.findByCustomerId(customer.getId());
       if (cart == null) {
           cart = new Cart();
           cart.setCustomer(customer);
           cart =  cartRepository.save(cart);
       }
       return cart;
    }

   public Cart addCart(Long itemId, Customer customer) {
       Cart cart = getDefaultCartForCustomer(customer);
       Optional<Item> itemOptional = itemRepository.findById(itemId);
       if (itemOptional.isPresent()) {
           Item item = itemOptional.get();
           cart.getItems().add(item);
           return cartRepository.save(cart);
       }
       return cart;
   }

   public Cart removeCart(Long itemId, Customer customer) {
        Cart cart = getDefaultCartForCustomer(customer);
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.getItems().remove(item);
            return cartRepository.save(cart);
        }
        return cart;
   }

   public void clearCart(Cart cart) {
        if (cart != null || !cart.getItems().isEmpty()) {
            cart.getItems().clear();
            cartRepository.save(cart);
        }
   }

}

