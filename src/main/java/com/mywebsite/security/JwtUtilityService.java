package com.mywebsite.security;

import com.mywebsite.users.db.WebsiteUserDAO;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUtilityService {

    public String createToken(WebsiteUserDAO user) {
        long currentTimeMillis = System.currentTimeMillis();
        long hourTimeInMillis = TimeUnit.HOURS.toMillis(1);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + hourTimeInMillis))
                .signWith(JwtKeys.getPrivateKey())
                .compact();
    }
}
