package com.example.vegan.global.config.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SessionUser {

    private String name;
    private String email;

    @Builder
    public SessionUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
