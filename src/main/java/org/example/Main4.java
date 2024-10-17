package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main4 {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
                .buildSessionFactory();
    }

}
