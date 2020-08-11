package com.mywebsite.security;

import com.mywebsite.exceptions.ApiError;
import com.mywebsite.utility.ObjectToJson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtFilter extends OncePerRequestFilter {

    private final String PREFIX = "Bearer ";
    private final AuthenticationEntryPoint authenticationEntryPoint;


    public JwtFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith(PREFIX)) {
            try {
                UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
                SecurityContextHolder.getContext().setAuthentication(authResult);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (InsufficientAuthenticationException e) {
                SecurityContextHolder.clearContext();
                authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
            } catch (JwtException ex) {
                ApiError apiError = new ApiError();
                apiError.setReason(Collections.singletonList("JWT expired or can not be trusted"));
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.getWriter().write(ObjectToJson.converToJsonString(apiError));
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(JwtKeys.getPublicKey()).build().parseClaimsJws(header.substring(PREFIX.length()));
        String username = claimsJws.getBody().get("email").toString();
        String role = claimsJws.getBody().get("role").toString();
        Set<SimpleGrantedAuthority> authority = Collections.singleton(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username, null, authority);
    }

}
