package com.example.vegan.global.config.jwt;


import com.example.vegan.domain.user.entity.User;
import com.example.vegan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(id).orElseThrow(() -> new UsernameNotFoundException(id));
        return new AuthUserDetail(user);
    }
}
