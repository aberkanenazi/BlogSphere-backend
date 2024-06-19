package com.aberkane.blogsphere.blogsphere_backend.controller;


import com.aberkane.blogsphere.blogsphere_backend.dto.PostDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.PostRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.model.Post;
import com.aberkane.blogsphere.blogsphere_backend.services.PostServiceImpl;
import com.aberkane.blogsphere.blogsphere_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin("http://localhost:3000")
public class PostController {

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public Page<PostDto> getAllPost(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/")
    public PostDto getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/")
    public PostDto createPost(@RequestBody PostRequestDto postCreateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getCurrentUser());
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setAuthor(userDetails.getCurrentUser());
        return postService.createPost(post);
    }

    @PutMapping("/")
    public PostDto updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("/my-posts")
    public Page<PostDto> getMyPosts(Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.getPostsByUserId(pageable, userDetails.getCurrentUser().getId());
    }




}
