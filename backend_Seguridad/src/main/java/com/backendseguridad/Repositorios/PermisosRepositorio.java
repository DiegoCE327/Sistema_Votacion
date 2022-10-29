package com.backendseguridad.Repositorios;

import com.backendseguridad.Modelos.Permisos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PermisosRepositorio extends MongoRepository<Permisos,String> {
    @Query("{'url':?0,'metodo':?1}")
    Permisos consultaUrlMetodo(String url, String metodo);
}
