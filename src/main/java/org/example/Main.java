package org.example;

// Importamos las librerías necesarias para trabajar con Hibernate y consultas SQL
import org.hibernate.Session;                 // Clase que representa una sesión con la base de datos (equivale a una transacción).
import org.hibernate.SessionFactory;          // Clase que crea objetos "Session" para interactuar con la base de datos.
import org.hibernate.cfg.Configuration;       // Clase usada para configurar Hibernate con el archivo hibernate.cfg.xml.
import org.hibernate.query.Query;             // Clase que permite crear y ejecutar consultas HQL (Hibernate Query Language).

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!"); // Salida inicial simple para comprobar que el programa se ejecuta.

        // Crear una SessionFactory que se encargará de construir sesiones (Session) para interactuar con la base de datos.
        // Usa el archivo de configuración de Hibernate (hibernate.cfg.xml) para conectarse a la base de datos.
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Bloque try-with-resources para asegurar que la sesión se cierra correctamente, incluso si hay errores.
        try (Session session = sessionFactory.openSession()) {
            // Crear una consulta para contar los registros de la tabla 'Usuario'.
            // Esta línea está comentada, es un ejemplo de cómo contar filas en una tabla.
            // Query<Long> query = session.createQuery("select count (i) from Usuario i", Long.class);

            // Esta consulta selecciona todos los registros de la tabla 'Usuario'.
            // Se usa HQL (Hibernate Query Language), un lenguaje similar a SQL pero orientado a objetos.
            Query<Usuario> query = session.createQuery("Select '*' from Usuario i, Usuario.class");

            // Ejecutar la consulta y obtener el resultado como una lista de objetos 'Usuario'.
            var resultado = query.list();

            // Iterar sobre los resultados de la consulta y mostrar cada objeto 'Usuario' por consola.
            resultado.forEach(System.out::println);

            // Iniciar una nueva transacción para modificar la base de datos.
            session.beginTransaction();

            // Crear un nuevo objeto 'Usuario' que será insertado en la base de datos.
            Usuario u = new Usuario();
            u.setId(2); // Asignamos un ID al usuario.
            u.setEmail("manolo@example.com"); // Asignamos un email.
            u.setPassword("fbqeifbewf"); // Asignamos una contraseña.
            u.setIsAdmin(true); // Asignamos que es administrador.

            // Guardar el nuevo objeto 'Usuario' en la base de datos (inserción).
            session.persist(u); // 'persist' guarda el objeto en el contexto de persistencia, es decir, en la base de datos.

            // Imprimir el objeto 'Usuario' recién guardado para verificar los datos.
            System.out.println(u);

            // Hacer commit de la transacción, confirmando y guardando los cambios en la base de datos.
            session.getTransaction().commit();
        }
    }
}
