package com.aberkane.blogsphere.blogsphere_backend.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long postId;
    private Long authorId;
}
