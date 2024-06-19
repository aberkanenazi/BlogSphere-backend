package com.aberkane.blogsphere.blogsphere_backend.services;

import java.util.List;

import com.aberkane.blogsphere.blogsphere_backend.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aberkane.blogsphere.blogsphere_backend.model.Post;


public interface PostService {

    Page<PostDto> getAllPosts(Pageable pageable);
    PostDto getPostById(Long id);
    PostDto createPost(Post post);
    PostDto updatePost(Post post);
    String deletePost(Long id);
    Page<PostDto> getPostsByUserId(Pageable pageable, Long userId);
} 
