package com.mywebsite.users.db;

import com.mywebsite.exceptions.UserNotFoundException;
import com.mywebsite.users.WebsiteUser;
import com.mywebsite.validators.EmailVerifier;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class UsersDAOImpl implements UsersDAO {

    private final EntityManager entityManager;

    @Autowired
    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<WebsiteUserDAO> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<WebsiteUserDAO> query = session.createQuery("FROM WebsiteUserDAO", WebsiteUserDAO.class);
        List<WebsiteUserDAO> list = query.getResultList();
        return list;
    }

    @Override
    public void addUser(WebsiteUser user, String role) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO userDAO = createWebsiteUserDAO(user, role);
        session.saveOrUpdate(userDAO);
    }

    @Override
    public WebsiteUserDAO getUser(UUID id) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO user = session.get(WebsiteUserDAO.class, id);
        if(user == null) {
            throw new UserNotFoundException("There is no user with id " + id);
        }
        return user;
    }

    @Override
    public void deleteUser(UUID id) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO userToDelete = session.get(WebsiteUserDAO.class, id);
        if(userToDelete == null) {
            throw new UserNotFoundException("There is no user with id " + id);
        }
        session.delete(userToDelete);
    }

    @Override
    public void updateUser(WebsiteUser user, String role) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO userToUpdate = getUserByEmail(user.getEmail());
        if(userToUpdate == null) {
            throw new UserNotFoundException("There is no such user");
        }
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setPassword(user.getPassword());
        if (!EmailVerifier.isEmailValid(user.getEmail())) {
            throw new IllegalArgumentException("Email has wrong structure");
        }
        if(!userToUpdate.getEmail().equals(user.getEmail())) {
            verifyIfEmailTaken(user.getEmail());
        }
        userToUpdate.setRole(role);
        session.update(userToUpdate);
    }

    private void verifyIfEmailTaken(String email) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery<String> sqlQuery = session.createSQLQuery("SELECT email FROM users WHERE email=:email");
        sqlQuery.setParameter("email", email);
        List<String> resultList = sqlQuery.getResultList();
        if (!resultList.isEmpty()) {
            throw new IllegalArgumentException("Email " + email + " already exists");
        }
    }

    private WebsiteUserDAO createWebsiteUserDAO(WebsiteUser user, String role) {
        WebsiteUserDAO userDAO = new WebsiteUserDAO();
        userDAO.setName(user.getName().trim());
        userDAO.setSurname(user.getSurname().trim());
        if (!EmailVerifier.isEmailValid(user.getEmail())) {
            throw new IllegalArgumentException("Email has wrong structure");
        }
        verifyIfEmailTaken(user.getEmail());
        userDAO.setPassword(user.getPassword()); // TODO: ADD PASSWORD HASHING WITH BCrypt(Spring Security);
        userDAO.setEmail(user.getEmail());
        userDAO.setRole(role);
        return userDAO;
    }

    private WebsiteUserDAO getUserByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery<String> sqlQuery = session.createSQLQuery("SELECT CAST(id as VARCHAR) AS VARCHAR FROM users WHERE email=:email");
        sqlQuery.setParameter("email", email);
        UUID userId = UUID.fromString(sqlQuery.getSingleResult());
        WebsiteUserDAO user = session.get(WebsiteUserDAO.class, userId);
        return user;
    }


}
