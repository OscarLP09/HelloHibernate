package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try( Session session = sessionFactory.openSession() ){
            //Query<Long> query = session.createQuery("select count (i) from Usuario i", Long.class);
            Query<Usuario> query = session.createQuery("Select '*' from Usuario i, Usuario.class");
            var resultado = query.list();
            resultado.forEach(System.out::println);

            session.beginTransaction();
            Usuario u = new Usuario();
            u.setId(2);
            u.setEmail("manolo@example.com");
            u.setPassword("fbqeifbewf");
            u.setIsAdmin(true);
            session.persist(u);// Persist sirve para Guardar el nuevo usuario
            System.out.println(u);
            session.getTransaction().commit();// Hacemos commit

        }
    }
}