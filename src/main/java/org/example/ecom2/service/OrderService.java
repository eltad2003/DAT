package org.example.ecom2.service;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Item;
import org.example.ecom2.model.Orders;
import org.example.ecom2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(Orders order) {
        orderRepository.save(order);
    }

    public List<Orders> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    public Orders createOrderFromCart(Cart cart) {
        Orders orders = new Orders();
        orders.setCustomer(cart.getCustomer());
        orders.setCart(cart);
        orders.setStatus("Processing");
        Orders savedOrder = orderRepository.save(orders);

        return savedOrder;
    }

    public Orders getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
