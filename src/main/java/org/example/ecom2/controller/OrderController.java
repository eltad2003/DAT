package org.example.ecom2.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Comment;
import org.example.ecom2.model.Customer;
import org.example.ecom2.model.Orders;
import org.example.ecom2.service.CartService;
import org.example.ecom2.service.CommentService;
import org.example.ecom2.service.OrderService;
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
    private OrderService orderService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerData");
        if (customer == null) {
            return "redirect:/login";
        }
        Cart cart = cartService.getDefaultCartForCustomer(customer);
        model.addAttribute("cart", cart);
        model.addAttribute("customerData", customer);
        return "checkout";
    }
    /**
     * Xử lý đặt hàng khi checkout.
     */
    @PostMapping("/order/checkout")
    public String checkout(Model model, HttpSession session ) {
        Customer customer = (Customer) session.getAttribute("customerData");
        if (customer == null) {
            return "redirect:/login";
        }
        Cart cart = cartService.getDefaultCartForCustomer(customer);
        if (cart.getItems().isEmpty()) {
            model.addAttribute("errorMessage", "No items in your cart.");
            return "redirect:/cart";
        }
        Orders order = orderService.createOrderFromCart(cart);
//        cartService.clearCart(cart);

        model.addAttribute("customerData", customer);
        model.addAttribute("order", order);
        return "order_success";
    }

    @GetMapping("/{customerId}/review")
    public String review(@PathVariable("customerId") Long customerId, Model model, HttpSession session) {
        Customer sessionCustomer = (Customer) session.getAttribute("customerData");
        if (sessionCustomer == null) {
            return "redirect:/login";
        }
        List<Comment> comments = commentService.getCommentsByCustomerId(customerId);
        model.addAttribute("comments", comments);
        model.addAttribute("customerData", sessionCustomer);
        return "review";
    }
    @GetMapping("/orders")
    public String listOrders(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customerData");
        if (customer == null) {
            return "redirect:/login";
        }
        List<Orders> orders = orderService.getOrdersByCustomerId(customer.getId());
        Cart cart = cartService.getDefaultCartForCustomer(customer);
        model.addAttribute("orders", orders);
        model.addAttribute("customerData", customer);
        model.addAttribute("cart", cart);
        return "list_orders";
    }
    @GetMapping("orders/{orderId}")
    public String viewOrderDetails(@PathVariable Long orderId, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customerData");
        if (customer == null) {
            return "redirect:/login";
        }
        Orders order = orderService.getOrderDetails(orderId);
        if (order == null) {
            return "redirect:/orders";
        }

        Cart cart = cartService.getDefaultCartForCustomer(customer);
        model.addAttribute("order", order);
//        model.addAttribute("cart", cart);
        model.addAttribute("customerData", customer);
        return "detail_order";
    }

}
