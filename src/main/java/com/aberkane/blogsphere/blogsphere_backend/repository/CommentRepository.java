package com.aberkane.blogsphere.blogsphere_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aberkane.blogsphere.blogsphere_backend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    Page<Comment> findByPostId(Pageable pageable, Long postId);

}
