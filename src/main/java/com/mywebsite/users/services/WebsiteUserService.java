package com.mywebsite.users.services;

import com.mywebsite.users.WebsiteUser;
import com.mywebsite.users.db.WebsiteUserDAO;

import java.util.List;
import java.util.UUID;

public interface WebsiteUserService {
    List<WebsiteUserDAO> getUsers();
    void addUser(WebsiteUser user, String role);
    WebsiteUserDAO getUser(UUID id);
    void deleteUser(UUID id);
}
