package com.backendseguridad.Controladores;


import com.backendseguridad.Modelos.Rol;
import com.backendseguridad.Modelos.Usuarios;
import com.backendseguridad.Repositorios.RolRepositorio;
import com.backendseguridad.Repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuariosControlador {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping("/consulta")
    public List<Usuarios> consultaUsuarios(){
        return usuariosRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/agregar")
    public Usuarios crearUsuario(@RequestBody Usuarios usuario){
        usuario.setContrasena(convertirSHA256(usuario.getContrasena()));
        return this.usuariosRepositorio.save(usuario);
    }

    @GetMapping("/consulta/{id}")
    public Usuarios consultaUsuarioID(@PathVariable String id){
        Usuarios ustActual=this.usuariosRepositorio.findById(id).orElse(null);
        return ustActual;
    }
    /**
     * Relación (1 a n) entre rol y usuario
     * @param //id
     * @param //id_rol
     * @return
     */

    @PutMapping("actualizar/{id}/rol/{id_rol}")
    public Usuarios asignarRolAUsuario(@PathVariable String id,@PathVariable String id_rol){
        Usuarios  usuarioActual=this.usuariosRepositorio.findById(id).orElseThrow(RuntimeException::new);
        Rol  rolActual=this.rolRepositorio.findById(id_rol).orElseThrow(RuntimeException::new);
        usuarioActual.setRol(rolActual);
        return this.usuariosRepositorio.save(usuarioActual);
    }

    @PutMapping("/actualizar/{id}")
    public Usuarios actualizarUsuario(@PathVariable String id,@RequestBody Usuarios usuario){

        Usuarios ustActual=this.usuariosRepositorio.findById(id).orElse(null);
        if (ustActual!=null){
            ustActual.setSeudonimo(usuario.getSeudonimo());
            ustActual.setCorreo(usuario.getCorreo());
            ustActual.setContrasena(convertirSHA256(usuario.getContrasena()))
            ;
            return this.usuariosRepositorio.save(ustActual);
        }else{
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable String id){
        Usuarios ustActual=this.usuariosRepositorio.findById(id).orElse(null);
        if (ustActual!=null){
            this.usuariosRepositorio.delete(ustActual);
        }
    }


    // VALIDACIONES DE USUARIOS
    @PostMapping("/validacion")
    public Usuarios validarUsuario(@RequestBody Usuarios usuario, final HttpServletResponse response) throws IOException {
            Usuarios validUsuario = this.usuariosRepositorio.busquedaPorCorreo(usuario.getCorreo());
            if (validUsuario != null && validUsuario.getContrasena().equals(convertirSHA256(usuario.getContrasena()))){
                validUsuario.setContrasena("");
                return validUsuario;
            }else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
