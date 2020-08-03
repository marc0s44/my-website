package com.mywebsite.users.controllers;

import com.mywebsite.users.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebsiteUserAuthController {

    private UserLoginService userLoginService;

    @Autowired
    public WebsiteUserAuthController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String header) {
        String token = userLoginService.login(header);
        return ResponseEntity.ok(token);
    }
}
