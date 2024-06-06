package com.aberkane.blogsphere.blogsphere_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aberkane.blogsphere.blogsphere_backend.model.Comment;
import com.aberkane.blogsphere.blogsphere_backend.repository.CommentRepository;

@Service
public class CommentServiceImpl  implements CommentService{

    CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> getCommentsByPostId(Pageable pageable, Long postId) {
        return commentRepository.findByPostId(pageable, postId);
    }

}
