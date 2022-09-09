package com.example.vegan.domain.like.service;

import com.example.vegan.domain.like.entity.Like;
import com.example.vegan.domain.like.repository.LikeRepository;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.repository.PostRepository;
import com.example.vegan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private boolean isNotAlreadyLike(Post post){
        return likeRepository.findByPost(post).isEmpty();
    }

    @Transactional
    public void insertLike(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found"));
        Like like = new Like(post);

        if(isNotAlreadyLike(post)){
            likeRepository.save(like);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void deleteLike(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found"));
        Like like = likeRepository.findByPost(post).orElseThrow(() -> new IllegalArgumentException("not found"));
        likeRepository.delete(like);
    }
}
