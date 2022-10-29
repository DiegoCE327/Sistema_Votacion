package com.backendseguridad.Controladores;


import com.backendseguridad.Modelos.Rol;
import com.backendseguridad.Repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolControlador {
    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping("/consulta")
    private List<Rol> consultarRoles(){
        return this.rolRepositorio.findAll();
    }

    @GetMapping("/consulta/{id}")
    private Rol consultaRolID(@PathVariable String id){
        Rol actualRol = this.rolRepositorio.findById(id).orElse(null);
        return  actualRol;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/agregar")
    private  Rol agregarRol(@RequestBody Rol rol){
        return this.rolRepositorio.save(rol);
    }

    @PutMapping("/actualizar/{id}")
    private Rol actualizarRol(@PathVariable String id,@RequestBody Rol rol){
            Rol actualRol = this.rolRepositorio.findById(id).orElse(null);
            if (actualRol != null){
                actualRol.setNombre(rol.getNombre());
                return rolRepositorio.save(actualRol);
            }else {
                return null;
            }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/eliminar/{id}")
    public void eliminarRol(@PathVariable String id){
        Rol actualRol=this.rolRepositorio.findById(id).orElse(null);
        if (actualRol!=null){
            this.rolRepositorio.delete(actualRol);
        }
    }
}
