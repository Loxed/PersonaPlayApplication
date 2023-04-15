package com.example.personaplayfront.Repo;


import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Model.Icon;
import com.example.personaplayfront.Model.Users;

import org.hibernate.Session;

import java.util.List;

public class UsersDaoImpl extends Dao<Users> {

    public UsersDaoImpl() {
        super(Users.class);
    }

    @Override
    public void save(Users users) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.persist(users);
            Icon icon = new Icon();
            session.persist(icon);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Users findById(int id) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.get(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Users users) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.merge(users);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            Users users = session.get(Users.class, id);
            session.remove(users);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Users> findAll() {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Users", Users.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users findByPropertyLike(String propertyName, Object propertyValue) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Users where " + propertyName + " like :propertyValue", Users.class)
                    .setParameter("propertyValue", "%" + propertyValue + "%")
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("No "+propertyName+" found!");
        }
        return null;
    }

    @Override
    public List<Users> findAllByPropertyLike(String propertyName, Object propertyValue) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Users where " + propertyName + " like :propertyValue", Users.class)
                    .setParameter("propertyValue", "%" + propertyValue + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}