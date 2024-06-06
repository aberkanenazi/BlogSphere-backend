package com.aberkane.blogsphere.blogsphere_backend.services;

import com.aberkane.blogsphere.blogsphere_backend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentService {

    Page<Comment> getAllComments(Pageable pageable);
    Comment getCommentById(Long id);
    Comment createComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Long id);
    Page<Comment> getCommentsByPostId(Pageable pageable, Long postId);
}
