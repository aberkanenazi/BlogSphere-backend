package com.aberkane.blogsphere.blogsphere_backend.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aberkane.blogsphere.blogsphere_backend.model.Post;


public interface PostService {

    Page<Post> getAllPosts(Pageable pageable);
    Post getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Post post);
    void deletePost(Long id);
    List<Post> getPostsByUserId(Long userId);
} 
