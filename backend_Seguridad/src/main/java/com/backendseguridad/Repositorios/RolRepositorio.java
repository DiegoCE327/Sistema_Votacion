package com.backendseguridad.Repositorios;

import com.backendseguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolRepositorio extends MongoRepository<Rol,String> {
}
