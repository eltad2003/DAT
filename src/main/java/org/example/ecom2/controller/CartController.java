package org.example.ecom2.controller;

import org.example.ecom2.model.Cart;

import org.example.ecom2.repository.CartRepository;
import org.example.ecom2.repository.ItemRepository;
import org.example.ecom2.service.CartService;
import org.example.ecom2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

//    view carts
    @GetMapping("/carts")
    public String showCarts(Model model) {
        // Giả sử bạn có một giỏ hàng mặc định với ID là 1
        Cart cart = cartService.getDefaultCart();

        model.addAttribute("cart", cart);
        return "carts";
    }

    //add item into cart
    @PostMapping("/item/{itemId}")
    public String addCart( @PathVariable Long itemId, Model model) {
        Cart cart = cartService.addCart(itemId);
        model.addAttribute("cart", cart);
        return "redirect:/carts";
    }
    //remove item from cart
    @PostMapping("/cart/remove/{itemId}")
    public String removeCart(@PathVariable Long itemId, Model model) {
        Cart cart = cartService.removeCart(itemId);
        model.addAttribute("cart", cart);
        return "redirect:/carts";
    }

}

