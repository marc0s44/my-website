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
@RequestMapping("/users")
@CrossOrigin
public class WebsiteUserController {

    @Autowired
    private WebsiteUserService websiteUserService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<WebsiteUserDAO> users = websiteUserService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody WebsiteUser user) {

        websiteUserService.addUser(user, "user");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        WebsiteUserDAO user = websiteUserService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        websiteUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@Valid @RequestBody WebsiteUser user) {
        websiteUserService.updateUser(user, "user");
        return ResponseEntity.ok().build();
    }
}
