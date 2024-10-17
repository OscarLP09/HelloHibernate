package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session = sessionFactory.openSession()){

            //Obtener de la lista el elemento cuyo Id sea = 1
            /*Usuario u45 = session.get(Usuario.class, 1); // Es como un select
            System.out.println(u45);


             */
            /*// Update en la lista
            session.beginTransaction();
            u45.setEmail("lola@gmail.com");
            u45.setIsAdmin(false);
            session.getTransaction().commit();
            */
            /*
            // Delete
            session.beginTransaction();
            session.remove(u45);
            session.getTransaction().commit();
*/
            // Consulta SQL en el que saco de la tabla todos los elementos que tengan el email que he puesto en el parámetro

            Query<Usuario> q = session.createQuery("select u from Usuario u where u.email=:email", Usuario.class);
            q.setParameter("email", "lola@gmail.com");
            List<Usuario> usuarios = q.getResultList();
            usuarios.forEach(System.out::println);
        }

    }
}
