package main;

import encapsulacion.Articulo;
import encapsulacion.Usuario;
import freemarker.template.Configuration;
import freemarker.template.Version;
import modelo.servicios.EntityServices.ArticuloService;
import modelo.servicios.EntityServices.UsuarioService;
import modelo.servicios.Utils.BootStrapService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) throws SQLException {

        BootStrapService.startDb();

        BootStrapService.crearTablas();
        
//        Usuario usuario = new Usuario(2l, "admin", "juan", "1234", false, true);


//        UsuarioService usuarioService = new UsuarioService();
//        usuarioService.insert(usuario);
        //usuarioService.delete(usuario);
        //usuarioService.update(usuario);

//        Usuario usuario = usuarioService.getById(1L);
//        System.out.println(usuario.getNombre());

//        Articulo articulo = new Articulo(2l, "Ta haciendo calor", "Esta haciendo el real calor, raro es que la gente no se ponga mala con este solaso que hace.", usuario, Date.valueOf(LocalDate.now()), null, null);
        ArticuloService  articuloService = new ArticuloService();
//        articuloService.insert(articulo);


      //  Etiqueta etiqueta = new Etiqueta(2l, "klk", articulo);
       // EtiquetaService etiquetaService = new EtiquetaService();
       // etiquetaService.delete(etiqueta);

        //Comentario comentario = new Comentario(5l, "el que el que", usuario, articulo);
     //   ComentarioService comentarioService = new ComentarioService();

     //   List<Comentario> list =comentarioService.getByArticulo(articulo.getId());
      //  System.out.println(Arrays.asList(list));

       // BootStrapService.stopDb();

        staticFiles.location("/templates");

        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");


            attributes.put("list", articuloService.getAll());
            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        get("/verMas/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = request.params("id");

            Articulo articulo = articuloService.getById(Integer.parseInt(idArticulo));
            attributes.put("articulo", articulo);

            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);

    }
}
