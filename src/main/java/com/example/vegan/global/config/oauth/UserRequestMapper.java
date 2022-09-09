package com.example.vegan.global.config.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public SessionUser toDto(OAuth2User oAuth2User){
        var attributes = oAuth2User.getAttributes();
        return SessionUser.builder()
                .email((String)attributes.get("email"))
                .name((String)attributes.get("name"))
                .build();
    }
}
