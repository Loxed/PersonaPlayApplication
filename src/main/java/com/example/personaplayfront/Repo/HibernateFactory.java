package com.example.personaplayfront.Repo;

import com.example.personaplayfront.Model.Medias;
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

        configuration.addAnnotatedClass(Medias.class);

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
