package com.mywebsite.security;

import com.mywebsite.exceptions.RestAccessDeniedHandler;
import com.mywebsite.exceptions.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authenticationEntryPoint = new RestAuthenticationEntryPoint();

    @Value("${secret.jwt.key.path}")
    private String privateKeyPath;

    @Value("${public.jwt.key.path}")
    private String publicKeyPath;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(new RestAccessDeniedHandler());

        http
                .authorizeRequests()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users", "/users/**").hasAuthority("user")
                .and()
                .addFilterBefore(new JwtFilter(authenticationEntryPoint), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Bean
    public JwtKeys jwtKeys() {
        JwtKeys jwtKeys = new JwtKeys();
        jwtKeys.setPrivateKey(privateKeyPath);
        jwtKeys.setPublicKey(publicKeyPath);
        return jwtKeys;
    }
}
