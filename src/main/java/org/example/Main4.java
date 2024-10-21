package org.example;

// Importamos las clases necesarias de Hibernate para manejar la sesión y la configuración.
import org.hibernate.SessionFactory;            // Clase que crea objetos "Session" para interactuar con la base de datos.
import org.hibernate.cfg.Configuration;         // Clase utilizada para configurar Hibernate utilizando el archivo hibernate.cfg.xml.

public class Main4 {
    public static void main(String[] args) {

        // Creamos una fábrica de sesiones (SessionFactory) para manejar las conexiones a la base de datos.
        // Usamos la configuración de Hibernate desde el archivo hibernate.cfg.xml.
        // Además, usamos el método setProperty() para asignar la contraseña de la base de datos de manera dinámica.
        SessionFactory sessionFactory = new Configuration()
                // Carga la configuración desde el archivo hibernate.cfg.xml.
                .configure()
                // Establece la propiedad de la contraseña de la base de datos.
                // La contraseña se obtiene de una variable de entorno llamada "DB_PASSWORD" por razones de seguridad.
                .setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"))
                // Finalmente, construimos la fábrica de sesiones (SessionFactory), que gestiona las conexiones a la base de datos.
                .buildSessionFactory();
    }
}
