package com.aberkane.blogsphere.blogsphere_backend.dto;

public class PostRequestDto {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
