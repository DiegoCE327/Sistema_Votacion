package com.backendseguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Usuarios")
public class Usuarios {
    @Id
    private String id;
    private String seudonimo;
    private String correo;
    private String contrasena;

    @DBRef
    private Rol rol;

}
