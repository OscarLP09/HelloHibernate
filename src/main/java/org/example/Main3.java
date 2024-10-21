package org.example;

// Importamos las clases necesarias de Hibernate para manejar la sesión y la configuración.
import org.hibernate.SessionFactory;            // Clase que crea objetos "Session" para interactuar con la base de datos.
import org.hibernate.cfg.Configuration;         // Clase que se usa para configurar Hibernate con el archivo hibernate.cfg.xml.

public class Main3 {
    public static void main(String[] args) {
        // Creamos una SessionFactory usando la configuración de Hibernate, pero con una particularidad:
        // Usamos `setProperty` para añadir manualmente la contraseña de la base de datos, que se toma de una variable de entorno.
        // Esto es útil cuando la contraseña no está presente en el archivo hibernate.cfg.xml por razones de seguridad.
        SessionFactory sessionFactory = new Configuration()
                // Establecemos la propiedad de la contraseña de la base de datos tomando el valor de la variable de entorno 'DB_PASSWORD'.
                .configure()
                .setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
                .buildSessionFactory(); // Construimos la fábrica de sesiones.

        // Usamos una estructura lambda para ejecutar operaciones en la sesión de forma más compacta y limpia.
        // inSession es una operación personalizada que ejecuta código dentro de una sesión sin necesidad de gestionarla manualmente.
        sessionFactory.inSession((session) -> {
            // Dentro de la sesión, obtenemos un usuario por su ID (1) usando `session.get()`.
            // Esto es equivalente a un "SELECT * FROM Usuario WHERE id = 1".
            System.out.println(session.get(Usuario.class, 1)); // Imprimimos el usuario encontrado en la consola.
        });

        // Otro bloque lambda que ejecuta operaciones dentro de una transacción (una secuencia de acciones en la base de datos).
        sessionFactory.inTransaction((session) -> {
            // Obtenemos el usuario con ID = 1 (similar al código anterior).
            Usuario u1 = session.get(Usuario.class, 1);
            // Actualizamos el email del usuario encontrado.
            u1.setEmail("lolalolita@example.com");
            // Al no haber commit explícito aquí, se asume que 'inTransaction' maneja automáticamente el commit.
        });
    }
}
