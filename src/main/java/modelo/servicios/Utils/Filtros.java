package modelo.servicios.Utils;

import main.Main;

import static spark.Spark.*;


public class Filtros {

    public void filtros(){


        before((request, response) -> {

            System.out.println("Ruta del request antes: " + request.pathInfo());

            if (request.pathInfo().equalsIgnoreCase("/agregarPost") || request.pathInfo().equalsIgnoreCase("/agregarUsuario") || request.pathInfo().equalsIgnoreCase("/agregarComentario")){

                if (Main.usuario == null  || !Main.usuario.getAdministrator()){

                    response.redirect("/errorPost");
                }
            }


        });


    }
}
