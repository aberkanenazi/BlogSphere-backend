package com.aberkane.blogsphere.blogsphere_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private Long postId;
    private Long authorId;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, Long postId, Long authorId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
    }
}
