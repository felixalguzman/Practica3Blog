package modelo.servicios.Utils;

import main.Main;

import static spark.Spark.*;


public class Filtros {

    public void filtros() {


        before((request, response) -> {

            System.out.println("Ruta del request antes: " + request.pathInfo());
            boolean error = false;

            if (request.pathInfo().equalsIgnoreCase("/agregarPost")) {


                if (Main.usuario == null) {
                    error = true;
                }
            }

            if (request.pathInfo().equalsIgnoreCase("/agregarUsuario")) {

                if (Main.usuario == null || !Main.usuario.getAdministrator())
                    error = true;

            }

            if (request.pathInfo().equalsIgnoreCase("/agregarComentario")) {

                if (Main.usuario == null)
                    error = true;
            }

            if (error)
                response.redirect("/errorPost");


        });


    }
}
