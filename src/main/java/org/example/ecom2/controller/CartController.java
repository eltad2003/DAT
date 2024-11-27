package org.example.ecom2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ecom2.model.Cart;

import org.example.ecom2.model.Customer;
import org.example.ecom2.repository.CartRepository;
import org.example.ecom2.service.CartService;
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

//    view carts
    @GetMapping("/carts")
    public String showCarts(Model model, HttpSession session) {
        // Giả sử bạn có một giỏ hàng mặc định với ID là 1
        Customer customerData = (Customer) session.getAttribute("customerData");
        if (customerData == null) {
            return "redirect:/login";
        }
        Cart cart = cartService.getDefaultCartForCustomer(customerData);
        model.addAttribute("customerData", customerData);
        model.addAttribute("cart", cart);
        return "carts";
    }

    //add item into cart
    @PostMapping("/item/{itemId}")
    public String addCart( @PathVariable Long itemId, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerData");
        if (customer == null) {
            return "redirect:/login";
        }
        Cart cart = cartService.addCart(itemId,customer);
        model.addAttribute("cart", cart);
        return "redirect:/carts";
    }
    //remove item from cart
    @PostMapping("/cart/remove/{itemId}")
    public String removeCart(@PathVariable Long itemId, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerData");
        Cart cart = cartService.removeCart(itemId, customer);
        model.addAttribute("cart", cart);
        return "redirect:/carts";
    }

}

