package com.mywebsite.users.db;

import com.mywebsite.users.WebsiteUser;

import java.util.List;
import java.util.UUID;

public interface UsersDAO {
    List<WebsiteUserDAO> getUsers();
    void addUser(WebsiteUser user, String role);
    WebsiteUserDAO getUser(UUID id);
    WebsiteUserDAO getUserByEmail(String email);
    void deleteUser(UUID id);
    void updateUser(WebsiteUser user, String role);
}
