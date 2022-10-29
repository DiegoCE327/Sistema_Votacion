package com.backendseguridad.Modelos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PermisosRoles {

    @Id
    private String id;
    @DBRef
    private Rol rol;
    @DBRef Permisos permisos;
}
