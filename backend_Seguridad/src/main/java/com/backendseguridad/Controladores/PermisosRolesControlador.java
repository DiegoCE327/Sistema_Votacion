package com.backendseguridad.Controladores;

import com.backendseguridad.Modelos.Permisos;
import com.backendseguridad.Modelos.PermisosRoles;
import com.backendseguridad.Modelos.Rol;
import com.backendseguridad.Repositorios.PermisosRepositorio;
import com.backendseguridad.Repositorios.PermisosRolesRepositorio;
import com.backendseguridad.Repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisosroles")
public class PermisosRolesControlador {

    private PermisosRepositorio permisosRepositorio;
    private PermisosRolesRepositorio permisosRolesRepositorio;
    private RolRepositorio rolRepositorio;

    @Autowired
    public PermisosRolesControlador(PermisosRepositorio permisosRepositorio, PermisosRolesRepositorio permisosRolesRepositorio, RolRepositorio rolRepositorio) {
        this.permisosRepositorio = permisosRepositorio;
        this.permisosRolesRepositorio = permisosRolesRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    @GetMapping("/consulta")
    public List<PermisosRoles> consultarPermisosRoles(){
        return this.permisosRolesRepositorio.findAll();
    }

    @GetMapping("/consulta/{id}")
    public PermisosRoles consultarPermisosRolesID(@PathVariable String id){
        PermisosRoles permisoRolesActual = this.permisosRolesRepositorio.findById(id).orElse(null);
        return permisoRolesActual;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles crearPermisoRoles(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles nuevoPermisoRol = new PermisosRoles();
        Rol actualRol = this.rolRepositorio.findById(id_rol).get();
        Permisos actualPermiso = this.permisosRepositorio.findById(id_permiso).get();

        if (actualRol != null && actualPermiso!= null){
            nuevoPermisoRol.setRol(actualRol);
            nuevoPermisoRol.setPermisos(actualPermiso);
            return this.permisosRolesRepositorio.save(nuevoPermisoRol);
        }else {
            return null;
        }
    }


    @PutMapping("actualizar/{id}")
    public PermisosRoles actualizarPermisosRoles(@PathVariable String id,@RequestBody PermisosRoles pr){
        PermisosRoles actualPermisoRoles = this.permisosRolesRepositorio.findById(id).orElse(null);
//        Rol actualRol = this.rolRepositorio.findById(id_rol).orElse(null);
//        Permisos actualPermiso = this.permisosRepositorio.findById(id_permiso).orElse(null);

        if (actualPermisoRoles != null ){
            actualPermisoRoles.setRol(pr.getRol());
            actualPermisoRoles.setPermisos(pr.getPermisos());
            return this.permisosRolesRepositorio.save(actualPermisoRoles);
        }else {
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPermisoRol(@PathVariable String id){
       PermisosRoles actualPermisoRol = this.permisosRolesRepositorio.findById(id).orElse(null);
       if (actualPermisoRol != null){
           this.permisosRolesRepositorio.delete(actualPermisoRol);
       }
    }

    //VALIDACION PERMIDOS DE ACCESO SEGUN SU ROL

    @GetMapping("/validacion/rol/{id_rol}")
    public PermisosRoles consultarPermiso(@PathVariable String id_rol,@RequestBody Permisos permiso){
            Permisos consultaPermiso = this.permisosRepositorio.consultaUrlMetodo(permiso.getUrl(),
                                                                                  permiso.getMetodo());
            Rol actualRol = this.rolRepositorio.findById(id_rol).get();
            if (consultaPermiso != null && actualRol != null){
                return this.permisosRolesRepositorio.consultaPermisoDeRol(actualRol.getId(),consultaPermiso.getId());
            }else {
                return null;
            }
    }


}
