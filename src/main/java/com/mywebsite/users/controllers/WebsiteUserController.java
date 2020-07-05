package com.mywebsite.users.controllers;

import com.mywebsite.users.WebsiteUser;
import com.mywebsite.users.db.WebsiteUserDAO;
import com.mywebsite.users.services.WebsiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WebsiteUserController {

    @Autowired
    private WebsiteUserService websiteUserService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<WebsiteUserDAO> users = websiteUserService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@Valid @RequestBody WebsiteUser user) {

        websiteUserService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        WebsiteUserDAO user = websiteUserService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        websiteUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
