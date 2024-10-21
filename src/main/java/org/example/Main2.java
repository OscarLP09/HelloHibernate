package org.example;

// Importamos las librerías necesarias de Hibernate para la gestión de sesiones y consultas
import org.hibernate.Session;                 // Clase que representa una sesión con la base de datos (equivale a una transacción).
import org.hibernate.SessionFactory;          // Clase que crea objetos "Session" para interactuar con la base de datos.
import org.hibernate.cfg.Configuration;       // Clase usada para configurar Hibernate mediante el archivo de configuración hibernate.cfg.xml.
import org.hibernate.query.Query;             // Clase que permite crear y ejecutar consultas HQL (Hibernate Query Language).

import java.util.List;                        // Importamos la clase List para manejar colecciones de resultados.

public class Main2 {
    public static void main(String[] args) {
        // Creamos una SessionFactory usando la configuración de Hibernate, lo que permite abrir sesiones para interactuar con la base de datos.
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Utilizamos try-with-resources para abrir una sesión con la base de datos y asegurar que se cierra automáticamente.
        try (Session session = sessionFactory.openSession()) {

            // Comentado: Ejemplo de cómo obtener un usuario por su ID.
            // session.get() es similar a un "SELECT" por clave primaria, en este caso busca un Usuario con ID = 1.

            Usuario u45 = session.get(Usuario.class, 1); // Es como un select
            System.out.println(u45); // Imprime los detalles del usuario encontrado.


            // Comentado: Ejemplo de cómo actualizar un usuario en la base de datos.

            session.beginTransaction(); // Iniciamos una transacción para agrupar las operaciones.
            u45.setEmail("lola@gmail.com"); // Actualizamos el email del usuario.
            u45.setIsAdmin(false); // Actualizamos el estado de administrador del usuario.
            session.getTransaction().commit(); // Hacemos commit de la transacción, guardando los cambios.


            // Comentado: Ejemplo de cómo eliminar un usuario de la base de datos.

            session.beginTransaction(); // Iniciamos una transacción.
            session.remove(u45); // Eliminamos el usuario de la base de datos.
            session.getTransaction().commit(); // Hacemos commit de la transacción, confirmando la eliminación.


            // Ejemplo activo: Consulta usando HQL para obtener usuarios que coincidan con un email específico.
            // Se crea una consulta HQL que selecciona todos los usuarios cuyo email sea el que se pasa por parámetro.
            Query<Usuario> q = session.createQuery("select u from Usuario u where u.email=:email", Usuario.class);

            // Se establece el parámetro ":email" con el valor "lola@gmail.com".
            q.setParameter("email", "lola@gmail.com");

            // Ejecutamos la consulta y obtenemos el resultado como una lista de objetos 'Usuario'.
            List<Usuario> usuarios = q.getResultList();

            // Imprimimos cada uno de los usuarios encontrados.
            usuarios.forEach(System.out::println);
        }
    }
}
