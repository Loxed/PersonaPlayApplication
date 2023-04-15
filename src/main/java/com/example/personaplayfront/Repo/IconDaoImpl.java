package com.example.personaplayfront.Repo;

import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Model.Icon;
import org.hibernate.Session;

import java.util.List;

public class IconDaoImpl extends Dao<Icon> {
    public IconDaoImpl() {
        super(Icon.class);
    }

    @Override
    public void save(Icon icon) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.persist(icon);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Icon findById(int id) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.get(Icon.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Icon icon) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            session.merge(icon);
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
            Icon icon = session.get(Icon.class, id);
            session.remove(icon);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Icon> findAll() {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            return session.createQuery("from Icon",Icon.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Icon findByPropertyLike(String propertyName, Object propertyValue) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            String hql = "select i from Icon i where i." + propertyName + " like :" + propertyName;
            return (Icon) session.createQuery(hql).setParameter(propertyName, propertyValue).uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Icon> findAllByPropertyLike(String propertyName, Object propertyValue) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            String hql = "select i from Icon i where i." + propertyName + " like :" + propertyName;
            return session.createQuery(hql).setParameter(propertyName, propertyValue).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
