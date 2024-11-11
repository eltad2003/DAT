package org.example.ecom2.controller;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Comment;
import org.example.ecom2.model.Customer;
import org.example.ecom2.service.CartService;
import org.example.ecom2.service.CommentService;
import org.example.ecom2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        Cart cart = cartService.getDefaultCart();
        Customer customer = cart.getCustomer();
        model.addAttribute("cart", cart);
        model.addAttribute("customer", customer);
        return "checkout"; // Trả về trang hiển thị thông tin mua hàng
    }

    @PostMapping("/order/checkout")
    public String checkout(@RequestParam("paymentMethod") String paymentMethod, Model model ) {
        Cart cart = cartService.getDefaultCart();
        Customer customer = cart.getCustomer();
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("customer", customer);
        return "order_success";
    }

    @GetMapping("/{customerId}/review")
    public String review(@PathVariable("customerId") Long customerId, Model model) {
        Cart cart = cartService.getDefaultCart();
        Customer customer = cart.getCustomer();
        List<Comment> comments = commentService.getCommentsByCustomerId(customerId);
        model.addAttribute("comments", comments);
        model.addAttribute("customer", customer);
        return "review";
    }
}
