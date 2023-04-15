package com.example.personaplayfront.Repo;

import com.example.personaplayfront.DAO.Dao;
import com.example.personaplayfront.Model.Medias;

import org.hibernate.Session;

import java.util.List;


public class MediaDaoImpl extends Dao<Medias> {

    public MediaDaoImpl() {
        super(Medias.class);

    }

    @Override
    public void save(Medias medias) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(medias);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medias findById(int id) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            return session.get(Medias.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Medias medias) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(medias);
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
            Medias medias = session.get(Medias.class, id);
            session.remove(medias);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Medias> findAll() {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            return session.createQuery("from Medias", Medias.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medias findByPropertyLike(String propertyName, Object value) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            String hql = "select m from Medias m where m." + propertyName + " like CONCAT('%', :value, '%')";
            return session.createQuery(hql, Medias.class)
                    .setParameter("value", value)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Medias> findAllByPropertyLike(String propertyName, Object value) {
        try (Session session = HibernateFactory.sessionFactory.openSession()) {
            //depending on the property, we may want to order by different things
//            String hql = "select m from Medias m where m." + propertyName + " like CONCAT('%', :value, '%') ORDER BY m.imdb_id ASC";
            String hql = "select m from Medias m where m."+ propertyName +" like CONCAT('%', :value, '%') ORDER BY m.imdbId ASC";


            return session.createQuery(hql, Medias.class)
                    .setParameter("value", value)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

//hql to select m from medias m where m.genres like CONCAT('%', :value, '%') ORDER BY m.imdb_id ASC;