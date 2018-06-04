package main;

import modelo.dao.UsuarioDAO;
import modelo.dao.UsuarioDAOImpl;
import modelo.servicios.BootStrapService;
import modelo.servicios.DBService;

import java.sql.SQLException;

import encapsulacion.Usuario;
import modelo.servicios.UsuarioService;

public class Main {

    public static void main(String[] args) throws SQLException {

        BootStrapService.startDb();

        BootStrapService.crearTablas();
        
        //Usuario usuario = new Usuario(1l, "admin", "felix", "1234", true, false);

        UsuarioService usuarioService = new UsuarioService();
        //usuarioService.insert(usuario);
        //usuarioService.delete(usuario);
        //usuarioService.update(usuario);

        Usuario usuario = usuarioService.getById(1L);
        System.out.println(usuario.getNombre());





    }
}
