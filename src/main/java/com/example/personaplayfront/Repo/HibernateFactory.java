package com.example.personaplayfront.Repo;

import com.example.personaplayfront.Model.*;
import com.example.personaplayfront.Model.CompositeKeys.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static HibernateFactory instance;

    public static SessionFactory sessionFactory;

    public HibernateFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/personaplay?useSSL=false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");

        //0NF
        configuration.addAnnotatedClass(Medias.class);
        configuration.addAnnotatedClass(Users.class);
        configuration.addAnnotatedClass(Roles.class);
        configuration.addAnnotatedClass(Stats.class);
        configuration.addAnnotatedClass(Tags.class);
        configuration.addAnnotatedClass(Watchlist.class);

        //1NF

        configuration.addAnnotatedClass(UsersMedias.class);
        configuration.addAnnotatedClass(UsersMediasKey.class);

        configuration.addAnnotatedClass(MediasStats.class);
        configuration.addAnnotatedClass(MediasStatsKey.class);

        configuration.addAnnotatedClass(WatchlistTags.class);
        configuration.addAnnotatedClass(WatchlistTagsKey.class);

        //2NF
        configuration.addAnnotatedClass(WatchlistMediasStats.class);
        configuration.addAnnotatedClass(WatchlistMediasStatsKey.class);

        HibernateFactory.sessionFactory = configuration.buildSessionFactory();
    }

    public static HibernateFactory getInstance() {
        if (instance == null) {
            synchronized (HibernateFactory.class) {
                if (instance == null) {
                    instance = new HibernateFactory();
                }
            }
        }
        return instance;
    }
}
