package com.mywebsite.users;

import com.mywebsite.db.UsersDAO;
import com.mywebsite.users.validators.userValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class websiteUserServiceImpl implements websiteUserService {

    private UsersDAO usersDAO;
    private userValidator userValidator;

    @Autowired
    public websiteUserServiceImpl(UsersDAO usersDAO, com.mywebsite.users.validators.userValidator userValidator) {
        this.usersDAO = usersDAO;
        this.userValidator = userValidator;
    }

    @Transactional
    @Override
    public List<websiteUser> getUsers() {
        return usersDAO.getUsers();
    }

    @Transactional
    @Override
    public websiteUser addUser(websiteUser user) {
        userValidator.checkIfUserSetProperly(user);
        return usersDAO.addUser(user);
    }

    @Transactional
    @Override
    public websiteUser getUser(UUID id) {
        return usersDAO.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(UUID id) {
        usersDAO.deleteUser(id);
    }
}
