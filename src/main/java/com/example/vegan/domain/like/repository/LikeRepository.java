package com.example.vegan.domain.like.repository;

import com.example.vegan.domain.like.entity.Like;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
}
