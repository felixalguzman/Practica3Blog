package main;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;
import modelo.dao.EtiquetaDAOImpl;
import modelo.servicios.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        BootStrapService.startDb();

        BootStrapService.crearTablas();
        
        Usuario usuario = new Usuario(1l, "admin", "felix", "1234", false, true);


        //UsuarioService usuarioService = new UsuarioService();
        //usuarioService.insert(usuario);
        //usuarioService.delete(usuario);
        //usuarioService.update(usuario);

//        Usuario usuario = usuarioService.getById(1L);
//        System.out.println(usuario.getNombre());

        Articulo articulo = new Articulo(1l, "Hola", "Este es el cuerpo del articulo", usuario, Date.valueOf(LocalDate.now()), null, null);
//        ArticuloService  articuloService = new ArticuloService();
       // articuloService.insert(articulo);


      //  Etiqueta etiqueta = new Etiqueta(2l, "klk", articulo);
       // EtiquetaService etiquetaService = new EtiquetaService();
       // etiquetaService.delete(etiqueta);

        //Comentario comentario = new Comentario(5l, "el que el que", usuario, articulo);
        ComentarioService comentarioService = new ComentarioService();

        List<Comentario> list =comentarioService.getByArticulo(articulo.getId());
        System.out.println(Arrays.asList(list));

       // BootStrapService.stopDb();



    }
}
