package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main3 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                // Hacemos un set en la propiedad contraseña que falta en el archivo xml
                .configure()
                .setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
                .buildSessionFactory();

        sessionFactory.inSession((session)->{
            System.out.println(session.get(Usuario.class,1));
        });

        sessionFactory.inTransaction((session -> {
            Usuario u1 = session.get(Usuario.class,1);
            u1.setEmail("lolalolita@example.com");
        }));

    }
}
