package com.mywebsite.db;

import com.mywebsite.users.websiteUser;

import java.util.List;
import java.util.UUID;

public interface UsersDAO {
    List<websiteUser> getUsers();
    websiteUser addUser(websiteUser user);
    websiteUser getUser(UUID id);
    void deleteUser(UUID id);
}
