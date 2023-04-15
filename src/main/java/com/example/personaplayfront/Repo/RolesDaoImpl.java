package com.example.personaplayfront.Repo;

import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Model.Roles;

import org.hibernate.Session;

import java.util.List;

public class RolesDaoImpl extends Dao<Roles> {
    public RolesDaoImpl() {
        super(Roles.class);
    }

    @Override
    public void save(Roles roles) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.persist(roles);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Roles findById(int id) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.get(Roles.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Roles roles) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.merge(roles);
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
            Roles roles = session.get(Roles.class, id);
            session.remove(roles);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Roles> findAll() {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Roles", Roles.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Roles findByPropertyLike(String propertyName, Object propertyValue) {
        if(propertyName.equals("name")) {
            return findByName((String) propertyValue);
        }
        return null;
    }

    @Override
    public List<Roles> findAllByPropertyLike(String propertyName, Object propertyValue) {
        return null;
    }
    public Roles findByName(String name) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Roles where name = :name", Roles.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
