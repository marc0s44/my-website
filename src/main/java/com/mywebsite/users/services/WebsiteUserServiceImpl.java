package com.mywebsite.users.services;

import com.mywebsite.users.WebsiteUser;
import com.mywebsite.users.db.UsersDAO;
import com.mywebsite.users.db.WebsiteUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class WebsiteUserServiceImpl implements WebsiteUserService {

    private UsersDAO usersDAO;

    @Autowired
    public WebsiteUserServiceImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Transactional
    @Override
    public List<WebsiteUserDAO> getUsers() {
        return usersDAO.getUsers();
    }

    @Transactional
    @Override
    public WebsiteUser addUser(WebsiteUser user) {
        return usersDAO.addUser(user);
    }

    @Transactional
    @Override
    public WebsiteUserDAO getUser(UUID id) {
        return usersDAO.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(UUID id) {
        usersDAO.deleteUser(id);
    }
}
