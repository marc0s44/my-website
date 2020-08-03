package com.mywebsite.users.services;

import com.mywebsite.exceptions.UserNotFoundException;
import com.mywebsite.users.db.UsersDAO;
import com.mywebsite.users.db.WebsiteUserDAO;
import com.mywebsite.utility.BasicDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private UsersDAO usersDAO;

    @Autowired
    public UserLoginService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public String login(String header) {
        String[] credentials = BasicDecoder.decode(header);
        WebsiteUserDAO user = usersDAO.getUserByEmail(credentials[0]);
        if(user == null) {
            throw new UserNotFoundException("Wrong username or user does not exist");
        }
        if(!user.getPassword().equals(credentials[1])) {
            throw new UserNotFoundException("Wrong password");
        }

        return "Right";
    }
}
