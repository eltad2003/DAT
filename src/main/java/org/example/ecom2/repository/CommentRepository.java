package org.example.ecom2.repository;


import org.example.ecom2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByCustomerId(Long customerId);
}
