package com.aberkane.blogsphere.blogsphere_backend.services;

import com.aberkane.blogsphere.blogsphere_backend.dto.CommentDto;
import com.aberkane.blogsphere.blogsphere_backend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentService {

    Page<CommentDto> getAllComments(Pageable pageable);
    CommentDto getCommentById(Long id);
    CommentDto createComment(Comment comment);
    CommentDto updateComment(Comment comment);
    String deleteComment(Long id);
    Page<CommentDto> getCommentsByPostId(Pageable pageable, Long postId);
}
