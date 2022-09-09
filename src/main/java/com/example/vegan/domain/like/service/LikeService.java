package com.example.vegan.domain.like.service;

import com.example.vegan.domain.like.entity.Like;
import com.example.vegan.domain.like.repository.LikeRepository;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.repository.PostRepository;
import com.example.vegan.domain.user.entity.User;
import com.example.vegan.domain.user.repository.UserRepository;
import com.example.vegan.global.config.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    private boolean isNotAlreadyLike(Post post, User user){
        return likeRepository.findByPostAndUser(post, user).isEmpty();
    }

    @Transactional
    public void insertLike(Long postId) {
        User user = userRepository.findById((long) authenticationFacade.getUserId()).orElseThrow(() -> new IllegalArgumentException("not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found"));
        Like like = new Like(post, user);

        if(isNotAlreadyLike(post, user)){
            likeRepository.save(like);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void deleteLike(Long postId) {
        User user = userRepository.findById((long) authenticationFacade.getUserId()).orElseThrow(() -> new IllegalArgumentException("not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found"));
        Like like = likeRepository.findByPostAndUser(post, user).orElseThrow(() -> new IllegalArgumentException("not found"));
        likeRepository.delete(like);
    }
}
