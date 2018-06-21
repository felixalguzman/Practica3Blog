package modelo.servicios.Utils;

import encapsulacion.Usuario;
import main.Main;

import static spark.Spark.*;


public class Filtros {

    public void filtros() {


        before((request, response) -> System.out.println(  "Ruta antes: " + request.pathInfo() ));

        before("/agregarPost", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null || !usuario.getAutor()){

                halt(401, "No tienes permiso para esta area");
            }

        });

        before("/agregarUsuario", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null || !usuario.getAdministrator()){

                halt(401, "No tienes permiso para esta area");
            }

        });

        before("/agregarComentario", (request, response) -> {

            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null){

                halt(401, "No tienes permiso para esta area");
            }

        });

    }


}
