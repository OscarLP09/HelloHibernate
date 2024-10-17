package org.example;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Data // Lombok
@Entity // Dice que esto es una entidad
@Table(name = "usuario") // En que tabla se va a almacenar la entidad

public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es el PK
    @Column(name = "id", nullable = false) // Indica la columna del ID
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

}
/*
Cada entidad tiene que tener minimo un PK.
Si el nombre del atributo es el mismo que el de la columna,
se puede quitar el @Column.

 */