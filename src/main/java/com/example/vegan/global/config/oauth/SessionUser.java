package com.example.vegan.global.config.oauth;

import com.example.vegan.domain.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getNickname();
        this.email = user.getEmail();
    }
}
