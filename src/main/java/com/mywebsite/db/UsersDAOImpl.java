package com.mywebsite.db;

import com.mywebsite.users.websiteUser;
import org.hibernate.Session;
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
    public List<websiteUser> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<websiteUser> query = session.createQuery("FROM websiteUser", websiteUser.class);
        List<websiteUser> list = query.getResultList();
        return list;
    }

    @Override
    public websiteUser addUser(websiteUser user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public websiteUser getUser(UUID id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(websiteUser.class, id);
    }


}
