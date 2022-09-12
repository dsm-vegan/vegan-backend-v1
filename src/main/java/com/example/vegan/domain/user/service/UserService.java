package com.example.vegan.domain.user.service;

import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.user.controller.dto.MyInfoRequest;
import com.example.vegan.domain.user.entity.User;
import com.example.vegan.domain.user.repository.UserRepository;
import com.example.vegan.global.config.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public User getMyInfo(){
        return userRepository.findByEmail(authenticationFacade.getUserInfo().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Transactional
    public List<Post> getMyPost(){
        User user = userRepository.findByEmail(authenticationFacade.getUserInfo().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        return user.getPosts();
    }

    @Transactional
    public void delete(){
        userRepository.deleteByEmail(authenticationFacade.getUserInfo().getEmail());
    }

    @Transactional
    public void editMyInfo(MyInfoRequest request){
        User user = userRepository.findByEmail(authenticationFacade.getUserInfo().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        user.update(request.getNickname(), request.getIntroduction());
    }
}
