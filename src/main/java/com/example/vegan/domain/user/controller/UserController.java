package com.example.vegan.domain.user.controller;

import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.user.controller.dto.MyInfoRequest;
import com.example.vegan.domain.user.entity.User;
import com.example.vegan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public User getMyInfo(){
        return userService.getMyInfo();
    }

    @GetMapping("/user/list")
    public List<Post> getMyPost(){
        return userService.getMyPost();
    }

    @DeleteMapping("/user")
    public void delete(){
        userService.delete();
    }

    @PatchMapping("/user/update")
    public void editMyInfo(@RequestBody MyInfoRequest request){
        userService.editMyInfo(request);
    }
}
