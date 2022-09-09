package com.example.vegan.global.config.jwt;

import com.example.vegan.global.config.security.UserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final UserDetailService userDetailsService;

    private String secretKey = "64yA642V7IaM7ZSE7Yq47Juo7Ja066eI7J207Iqk7YSw6rOg65Ox7ZWZ6rWQIDLtlZnrhYQgMuuwmCA567KIIOuwle2cmOydkeyeheuLiOuLpC4g7KCA64qUIOyKpO2UhOungeydhCDqs7XrtoDtlZjqs6DsnojsirXri4jri6Qu";

    public String generateAccessToken(String userId){return makingToken(userId, "access", 7200L);}

    public String generateRefreshToken(String userId){return makingToken(userId, "refresh", 172800L);}

    public boolean validateAccessToken(String token) {
        return validateToken(token);
    }

    public boolean validateRefreshToken(String token){return validateToken(token);}

    public String getId(String token){
        try {
            return Jwts.parser().setSigningKey(encodingSecretKey()).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e){
            throw null;
        }
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){

            return token.substring(7);
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        UserDetails details = userDetailsService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(details, "",details.getAuthorities());
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String makingToken(String value, String type, Long time){
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + (time * 1000L)))
                .signWith(SignatureAlgorithm.HS512, encodingSecretKey())
                .setIssuedAt(new Date())
                .setSubject(value)
                .claim("type",type)
                .compact();
    }

    public String encodingSecretKey(){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
