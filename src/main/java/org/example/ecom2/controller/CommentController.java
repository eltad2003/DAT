package org.example.ecom2.controller;

import org.example.ecom2.model.Cart;
import org.example.ecom2.model.Comment;
import org.example.ecom2.model.Customer;
import org.example.ecom2.service.CartService;
import org.example.ecom2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/{customerId}/review/comment")
    public String rating(@PathVariable("customerId") Long customerId,Comment comment) {
        commentService.addComment(comment, customerId);
        return "redirect:/" + customerId + "/review";
    }

}
