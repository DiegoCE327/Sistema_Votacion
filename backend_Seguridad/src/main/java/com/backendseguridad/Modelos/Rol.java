package com.backendseguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rol {
    @Id
    private String id;
    private String nombre;
}
