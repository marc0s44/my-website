package com.mywebsite.users.db;

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

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<WebsiteUserDAO> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<WebsiteUserDAO> query = session.createQuery("FROM WebsiteUserDAO", WebsiteUserDAO.class);
        List<WebsiteUserDAO> list = query.getResultList();
        return list;
    }

    @Override
    public WebsiteUser addUser(WebsiteUser user) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO userDAO = createWebsiteUserDAO(user);
        session.saveOrUpdate(userDAO);
        return user;
    }

    @Override
    public WebsiteUserDAO getUser(UUID id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(WebsiteUserDAO.class, id);
    }

    @Override
    public void deleteUser(UUID id) {
        Session session = entityManager.unwrap(Session.class);
        WebsiteUserDAO userToDelete = session.get(WebsiteUserDAO.class, id);
        session.delete(userToDelete);
    }

    @Override
    public boolean isEmailTaken(String email) {
        Session session = entityManager.unwrap(Session.class);
        NativeQuery<String> sqlQuery = session.createSQLQuery("SELECT email FROM users WHERE email=:email");
        sqlQuery.setParameter("email", email);
        List<String> resultList = sqlQuery.getResultList();
        if(!resultList.isEmpty()) {
            return true;
        }
        throw new IllegalArgumentException("Email " + email + " already exists");
    }

    private WebsiteUserDAO createWebsiteUserDAO(WebsiteUser user) {
        WebsiteUserDAO userDAO = new WebsiteUserDAO();
        userDAO.setName(user.getName());
        userDAO.setSurname(user.getSurname());
        if(!EmailVerifier.isEmailValid(user.getEmail())) {
            throw new IllegalArgumentException("Email has wrong structure");
        }
        userDAO.setPassword(user.getPassword()); // TODO: ADD PASSWORD HASHING WITH BCrypt(Spring Security);
        userDAO.setEmail(user.getEmail());
        return userDAO;
    }


}
