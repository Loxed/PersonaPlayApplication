package com.example.personaplayfront.Repo;

import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Model.CompositeKeys.UsersMediasKey;
import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Model.UsersMedias;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UsersMediasDaoImpl extends Dao<UsersMedias> {

    public UsersMediasDaoImpl() {
        super(UsersMedias.class);
    }

    @Override
    public void save(UsersMedias usersMedias) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            session.beginTransaction();
            // Load the associated Users and Medias objects using the same session
            Users user = session.load(Users.class, usersMedias.getUser().getId());
            Medias media = session.load(Medias.class, usersMedias.getMedia().id);
            // Set the associated Users and Medias objects to the UsersMedias object
            usersMedias.setUser(user);
            usersMedias.setMedia(media);

            session.persist(usersMedias);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UsersMedias findById(int id) {
        System.out.println("findById(id) is not supported for UsersMediasDaoImpl. Use findById(usersId, mediasId) instead.");
        return null;
    }

    public UsersMedias findById(int usersId, int mediasId) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            return session.get(UsersMedias.class, new UsersMediasKey(usersId, mediasId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(UsersMedias usersMedias) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            session.beginTransaction();
            // Use merge() to update the UsersMedias object
            session.merge(usersMedias);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean deleteById(int id) {
        System.out.println("deleteById(id) is not supported for UsersMediasDaoImpl. Use deleteById(usersId, mediasId) instead.");
        return false;
    }

    public boolean deleteById(int usersId, int mediasId) {
        try {
            Session session = HibernateFactory.sessionFactory.openSession();
            session.beginTransaction();
            UsersMedias usersMedias = session.get(UsersMedias.class, new UsersMediasKey(usersId, mediasId));
            session.remove(usersMedias);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UsersMedias> findAll() {
        List<UsersMedias> usersMediasList = null;

        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            // Create a query to retrieve all UsersMedias objects
            Query<UsersMedias> query = session.createQuery("FROM UsersMedias", UsersMedias.class);
            usersMediasList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersMediasList;
    }

    public UsersMedias findByPropertyLike(String propertyName, Object propertyValue) {
        UsersMedias usersMedias = null;

        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            // Create a query to retrieve a UsersMedias object by a property value
            Query<UsersMedias> query = session.createQuery("FROM UsersMedias WHERE " + propertyName + " LIKE :propertyValue", UsersMedias.class);
            query.setParameter("propertyValue", propertyValue);
            usersMedias = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersMedias;
    }


    @Override
    public List<UsersMedias> findAllByPropertyLike(String propertyName, Object propertyValue) {
        List<UsersMedias> usersMediasList = null;

        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            // Create a query to retrieve a list of UsersMedias objects by a property value
            Query<UsersMedias> query = session.createQuery("FROM UsersMedias WHERE " + propertyName + " LIKE :propertyValue", UsersMedias.class);
            query.setParameter("propertyValue", propertyValue);
            usersMediasList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersMediasList;
    }

//    public void updateOrInsert(UsersMedias usersMedias) {
//        try (Session session = HibernateFactory.sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.merge(usersMedias);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
