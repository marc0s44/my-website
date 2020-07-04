package com.mywebsite.users;

import java.util.List;
import java.util.UUID;

public interface websiteUserService {
    List<websiteUser> getUsers();
    websiteUser addUser(websiteUser user);
    websiteUser getUser(UUID id);
    void deleteUser(UUID id);
}
