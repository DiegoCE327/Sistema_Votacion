package Votaciones.ModuloSeguridad.Repositorios;

import Votaciones.ModuloSeguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RepositorioRol extends MongoRepository<Rol,String> {
}
