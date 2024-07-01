package com.aberkane.blogsphere.blogsphere_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long authorId;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
    }
}
