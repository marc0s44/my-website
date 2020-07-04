package com.mywebsite.users;

import com.mywebsite.db.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class websiteUserServiceImpl implements websiteUserService {

    @Autowired
    private UsersDAO usersDAO;

    @Transactional
    @Override
    public List<websiteUser> getUsers() {
        return usersDAO.getUsers();
    }

    @Transactional
    @Override
    public websiteUser addUser(websiteUser user) {
        return usersDAO.addUser(user);
    }

    @Transactional
    @Override
    public websiteUser getUser(UUID id) {
        return usersDAO.getUser(id);
    }
}
