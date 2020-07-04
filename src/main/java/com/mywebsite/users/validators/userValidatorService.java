package com.mywebsite.users.validators;

import com.mywebsite.users.websiteUser;
import org.springframework.stereotype.Service;

@Service
public class userValidatorService implements userValidator {

    private boolean checkName(websiteUser user) {
        if (user != null && user.getName() != null && !user.getName().trim().equals("")) {
            return true;
        }
        throw new IllegalArgumentException("User name is not set");
    }

    private boolean checkSurname(websiteUser user) {
        if (user != null && user.getSurname() != null && !user.getSurname().trim().equals("")) {
            return true;
        }
        throw new IllegalArgumentException("User surname is not set");
    }

    private boolean checkPassword(websiteUser user) {
        if(user != null && user.getPassword() != null && !user.getPassword().trim().equals("")) {
            return true;
        }
        throw  new IllegalArgumentException("User password is not set");
    }

    private boolean checkEmail(websiteUser user) {
        if(user != null && user.getEmail() != null && !user.getEmail().trim().equals("")) {
            return true;
        }
        throw  new IllegalArgumentException("User email is not set");
    }

    @Override
    public boolean checkIfUserSetProperly(websiteUser user) {
        if(user == null) {
            throw new IllegalArgumentException("User attributes are needed to create user");
        } else {
            return checkName(user) && checkSurname(user) && checkPassword(user) && checkEmail(user);
        }
    }
}
