package com.example.vegan.domain.post.repository;

import com.example.vegan.domain.post.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
