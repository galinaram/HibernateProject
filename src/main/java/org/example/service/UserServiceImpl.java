package org.example.service;


import org.example.HibernateUtil;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class UserServiceImpl implements UserService{
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public User createUser(String name, String email, Integer age, LocalDateTime created_at) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();

            User user = new User(name, email, age, created_at);
            session.persist(user);
            session.getTransaction().commit();
            return user;
        } catch (Exception e){
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User readUser(int id) {
        Session session = factory.openSession();
        try {
            return (User) session.get(User.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(int id, Integer age) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user != null){
                user.setAge(age);
                session.merge(user);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(int id) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user != null){
                session.delete(user);
            }
            session.getTransaction().commit();

        } catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
    }
}
