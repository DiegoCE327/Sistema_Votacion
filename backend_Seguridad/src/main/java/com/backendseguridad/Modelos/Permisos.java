package com.backendseguridad.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Permisos {
    @Id
    private String id;
    private String url;
    private String metodo;
}