package com.aberkane.blogsphere.blogsphere_backend.controller;

import com.aberkane.blogsphere.blogsphere_backend.dto.CommentDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.CommentRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.model.Comment;
import com.aberkane.blogsphere.blogsphere_backend.model.User;
import com.aberkane.blogsphere.blogsphere_backend.services.CommentServiceImpl;
import com.aberkane.blogsphere.blogsphere_backend.services.UserDetailsImpl;
import com.aberkane.blogsphere.blogsphere_backend.services.UserDetailsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin("http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/")
    public Page<CommentDto> getAllComments(Pageable pageable) {
        return commentService.getAllComments(pageable);
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/")
    public CommentDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto) {
        System.out.println("Entered createComment");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);

        Comment comment = new Comment();
        comment.setContent(commentRequestDto.getContent());
        User user = userDetailsService.loadUser(userDetails.getUsername());
        comment.setUser(user);
        return commentService.createComment(comment);
    }

    @PutMapping("/")
    public CommentDto updateComment(@RequestBody Comment comment) {
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @GetMapping("/post/{postId}")
    public Page<CommentDto> getCommentsByPostId(Pageable pageable, @PathVariable Long postId) {
        return commentService.getCommentsByPostId(pageable, postId);
    }

}
