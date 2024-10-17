package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()){

            //Obtener de la lista el elemento cuyo Id sea = 1
            Usuario u45 = session.get(Usuario.class, 1);
            System.out.println(u45);

            // Update en la lista
            session.beginTransaction();
            u45.setEmail("lola@gmail.com");
            session.getTransaction().commit();
        }
    }
}
