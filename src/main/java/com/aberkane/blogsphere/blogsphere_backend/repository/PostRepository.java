package com.aberkane.blogsphere.blogsphere_backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aberkane.blogsphere.blogsphere_backend.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByAuthorId(Pageable pageable, Long authorId);

}
