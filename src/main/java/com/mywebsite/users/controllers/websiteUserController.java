package com.mywebsite.users.controllers;

import com.mywebsite.users.websiteUser;
import com.mywebsite.users.websiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class websiteUserController {

    @Autowired
    private websiteUserService websiteUserService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<websiteUser> users = websiteUserService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody websiteUser user) {
        websiteUserService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        websiteUser user = websiteUserService.getUser(id);
        return ResponseEntity.ok(user);
    }
}
