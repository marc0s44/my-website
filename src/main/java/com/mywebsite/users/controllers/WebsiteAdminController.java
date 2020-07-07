package com.mywebsite.users.controllers;

import com.mywebsite.users.WebsiteUser;
import com.mywebsite.users.services.WebsiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class WebsiteAdminController {

    private WebsiteUserService websiteUserService;

    @Autowired
    public WebsiteAdminController(WebsiteUserService websiteUserService) {
        this.websiteUserService = websiteUserService;
    }

    @PostMapping("/admins")
    public ResponseEntity<?> register(@Valid @RequestBody WebsiteUser user) {
        websiteUserService.addUser(user, "admin");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
