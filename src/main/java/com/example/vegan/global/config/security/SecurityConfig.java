package com.example.vegan.global.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "css/**", "/images/**", "/js/**", "/h2/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint().userService(customOAuth2UserService);
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
