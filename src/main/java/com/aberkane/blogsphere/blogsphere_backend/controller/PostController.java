package com.aberkane.blogsphere.blogsphere_backend.controller;


import com.aberkane.blogsphere.blogsphere_backend.dto.PostDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.PostRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.model.Post;
import com.aberkane.blogsphere.blogsphere_backend.model.User;
import com.aberkane.blogsphere.blogsphere_backend.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin("http://localhost:3000")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/")
    public ResponseEntity<Page<PostDto>> getAllPost(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<PostDto> posts = postService.getAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/")
    public PostDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        System.out.println("Entered createPost");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);


        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        User user = userDetailsService.loadUser(userDetails.getUsername());
        post.setAuthor(user);
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
    public ResponseEntity<Page<PostDto>> getMyPosts(@PageableDefault(page = 0, size = 10)Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetailsService.loadUser(userDetails.getUsername());
        return ResponseEntity.ok(postService.getPostsByUserId(pageable, user.getId()));
    }




}
