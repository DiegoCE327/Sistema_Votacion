package com.backendseguridad.Controladores;

import com.backendseguridad.Modelos.Permisos;
import com.backendseguridad.Repositorios.PermisosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class PermisosControlador {
    @Autowired
    private PermisosRepositorio permisosRepositorio;

    @GetMapping("/consulta")
    public List<Permisos> consultarTodosPermisos(){
        return this.permisosRepositorio.findAll();
    }

    @GetMapping("/consulta/{id}")
    public Permisos consultarPermisoID(@PathVariable String id){
        Permisos actualPermiso = this.permisosRepositorio.findById(id).orElse(null);
        return actualPermiso;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/agregar")
    public Permisos agregarPermiso(@RequestBody Permisos permiso){
        return this.permisosRepositorio.save(permiso);
    }

    @PutMapping("/actualizar/{id}")
    public  Permisos actualizarPermiso(@PathVariable String id,@RequestBody Permisos permiso){
        Permisos actualPermiso = this.permisosRepositorio.findById(id).orElse(null);
        if (actualPermiso != null){
            actualPermiso.setUrl(permiso.getUrl());
            actualPermiso.setMetodo(permiso.getMetodo());
            return this.permisosRepositorio.save(actualPermiso);
        }else {
            return  null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPermiso(@PathVariable String id){
        Permisos actualPermiso = this.permisosRepositorio.findById(id).orElse(null);
        if (actualPermiso != null){
            this.permisosRepositorio.delete(actualPermiso);
        }
    }

}
