package com.aberkane.blogsphere.blogsphere_backend.services;

import java.util.List;

import com.aberkane.blogsphere.blogsphere_backend.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aberkane.blogsphere.blogsphere_backend.model.Post;
import com.aberkane.blogsphere.blogsphere_backend.repository.PostRepository;

@Service
public class PostServiceImpl  implements PostService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PostDto> getAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostDto> postDtos = posts.map(post -> modelMapper.map(post, PostDto.class));

        return postDtos;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto createPost(Post post) {
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(Post post) {
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public String deletePost(Long id) {
        postRepository.deleteById(id);
        return  "Successfully deleted";
    }

    @Override
    public Page<PostDto> getPostsByUserId(Pageable pageable, Long userId) {
        Page<Post> posts = postRepository.findByAuthorId(pageable, userId);
        Page<PostDto> postDtos = posts.map(post -> modelMapper.map(post, PostDto.class));
        return postDtos;
    }
}
