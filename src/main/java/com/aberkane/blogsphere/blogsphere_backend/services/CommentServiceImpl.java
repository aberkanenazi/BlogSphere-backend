package com.aberkane.blogsphere.blogsphere_backend.services;

import com.aberkane.blogsphere.blogsphere_backend.dto.CommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aberkane.blogsphere.blogsphere_backend.model.Comment;
import com.aberkane.blogsphere.blogsphere_backend.repository.CommentRepository;

@Service
public class CommentServiceImpl  implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CommentDto> getAllComments(Pageable pageable) {
        return modelMapper.map(commentRepository.findAll(pageable), new TypeToken<Page<CommentDto>>(){}.getType());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new RuntimeException("Comment not found"));
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto createComment(Comment comment) {
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto updateComment(Comment comment) {
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public Page<CommentDto> getCommentsByPostId(Pageable pageable, Long postId) {
        return modelMapper.map(commentRepository.findByPostId(pageable, postId), new TypeToken<Page<CommentDto>>(){}.getType());
    }

}
