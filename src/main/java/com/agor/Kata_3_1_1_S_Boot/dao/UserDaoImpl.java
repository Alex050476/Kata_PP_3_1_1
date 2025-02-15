package com.agor.Kata_3_1_1_S_Boot.dao;

import com.agor.Kata_3_1_1_S_Boot.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void refactorUser(int id, User userUpdate) {
        String newName = userUpdate.getName();
        String newNickName = userUpdate.getNickname();
        Query query = entityManager.createQuery("UPDATE User u SET u.name = :newName, u.nickname = :newNickName WHERE id= :id");
        query.setParameter("newName", newName);
        query.setParameter("newNickName", newNickName);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("DELETE from User u WHERE u.id= :id");
        query.setParameter("id", id);
        int c = query.executeUpdate();
        System.out.println(c);
    }

    @Override
    public List<User> usersList() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery("from User WHERE id= :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

}