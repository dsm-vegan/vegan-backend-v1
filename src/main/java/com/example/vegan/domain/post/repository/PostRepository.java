package com.example.vegan.domain.post.repository;

import com.example.vegan.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
