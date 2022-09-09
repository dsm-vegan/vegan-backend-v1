package com.example.vegan.global.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public int getUserId(){
        return Integer.parseInt(((AuthUserDetail)getAuthentication().getPrincipal()).getUsername());
    }
}
