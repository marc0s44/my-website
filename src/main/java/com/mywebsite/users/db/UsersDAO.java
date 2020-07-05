package com.mywebsite.users.db;

import com.mywebsite.users.WebsiteUser;

import java.util.List;
import java.util.UUID;

public interface UsersDAO {
    List<WebsiteUserDAO> getUsers();
    void addUser(WebsiteUser user);
    WebsiteUserDAO getUser(UUID id);
    void deleteUser(UUID id);
}
