package com.example.vegan.global.config.security;

import com.example.vegan.global.config.oauth.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public SessionUser getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SessionUser) authentication.getPrincipal();
    }
}
