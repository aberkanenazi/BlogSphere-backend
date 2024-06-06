package com.aberkane.blogsphere.blogsphere_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aberkane.blogsphere.blogsphere_backend.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByUserId(Long userId);

}
