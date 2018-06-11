package main;

import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;
import freemarker.template.Configuration;
import freemarker.template.Version;
import modelo.servicios.EntityServices.ArticuloService;
import modelo.servicios.EntityServices.ComentarioService;
import modelo.servicios.EntityServices.EtiquetaService;
import modelo.servicios.EntityServices.UsuarioService;
import modelo.servicios.Utils.BootStrapService;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;
import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    static Usuario usuario;
    public static void main(String[] args) throws SQLException {

        BootStrapService.startDb();

        BootStrapService.crearTablas();
        


        UsuarioService usuarioService = new UsuarioService();
        ArticuloService  articuloService = new ArticuloService();
        EtiquetaService etiquetaService = new EtiquetaService();
        ComentarioService comentarioService = new ComentarioService();






        staticFiles.location("/templates");

        Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/templates");

        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        get("/inicio", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");

            attributes.put("etiquetas", etiquetaService.getAll());
            attributes.put("list", articuloService.getAll());

            return new ModelAndView(attributes, "inicio.ftl");
        }, freeMarkerEngine);


        get("/verMas/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = request.params("id");

            Articulo articulo2 = articuloService.getById(Integer.parseInt(idArticulo));
            attributes.put("articulo", articulo2);
            attributes.put("comentarios", comentarioService.getByArticulo(Integer.parseInt(idArticulo)));
            attributes.put("etiquetas", etiquetaService.getByArticulo(Integer.parseInt(idArticulo)));

            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);


        post("/agregarComentario", (request, response) -> {

            String comentario = request.queryParams("comentario");
            String articulo = request.queryParams("articulo");
            String autor = request.queryParams("autor");

            Usuario usuario1 = usuarioService.getById(Integer.parseInt(autor));
            Articulo articulo1 = articuloService.getById(Integer.parseInt(articulo));

            comentarioService.insert(new Comentario(comentario, usuario1, articulo1));



            response.redirect("/verMas/" + articulo);
            return  "";
        });

        get("/agregarPost", (request, response) -> configuration.getTemplate("agregarPost.ftl"));


        post("/guardarPost", (request, response) -> {
            Usuario autor = usuarioService.getById(2L);
            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            long now = System.currentTimeMillis();
            java.sql.Date nowsql = new java.sql.Date(now);

            String etiquetas = request.queryParams("etiquetas");

            String[] tagsarray = etiquetas.split(",");
            Long articleid = articuloService.getNextID();
            Articulo art = new Articulo(titulo, cuerpo, autor, nowsql);
            articuloService.insert(art);


            for(String s : tagsarray){
                Etiqueta e = new Etiqueta(s, articuloService.getById(articleid));
                etiquetaService.insert(e);
            }


            response.redirect("/");
            return "";
        });

        post("/iniciarSesion", (request, response) -> {

            String user = request.queryParams("usuario");
            String contra = request.queryParams("password");

            System.out.println(user + " pass : " + contra);

            Usuario usuario1 = usuarioService.validateLogIn(user, contra);

            if (usuario1 != null)
            {
                usuario = usuario1;
                response.redirect("/inicio");
            }



            return "";
        });



    }
}
