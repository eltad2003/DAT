package org.example.ecom2.service;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Comment;
import org.example.ecom2.model.Customer;
import org.example.ecom2.repository.CartRepository;
import org.example.ecom2.repository.CommentRepository;
import org.example.ecom2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Comment> getCommentsByCustomerId(Long customerId) {
        return commentRepository.findCommentByCustomerId(customerId);
    }

    public void addComment(Comment comment, Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            comment.setCustomer(customer);
            commentRepository.save(comment);
        }
    }
}
