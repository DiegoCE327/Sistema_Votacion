package com.backendseguridad.Repositorios;

import com.backendseguridad.Modelos.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsuariosRepositorio extends MongoRepository<Usuarios,String> {
    @Query("{'correo':?0}")
    public Usuarios busquedaPorCorreo(String correo);

}
