package modelo.servicios.Utils;

import encapsulacion.User;
import io.javalin.Javalin;


public class Filters {

    public Filters(Javalin app) {


        app.before((context) -> System.out.println("Ruta antes: " + context.fullUrl()));

        app.before("/agregarPost", (context) -> {

            User user = context.sessionAttribute("usuario");

            if (user == null || !user.getAutor()) {

//                halt(401, "No tienes permiso para esta area");
                context.status(401);
                context.redirect("/errorPost/401");
            }

        });

        app.before("/agregarUsuario", (context) -> {

            User user = context.sessionAttribute("usuario");

            if (user == null || !user.getAdministrator()) {

                context.status(401);
            }

        });

        app.before("/agregarComentario", (context) -> {

            User user = context.sessionAttribute("usuario");

            if (user == null) {

//                halt(401, "No tienes permiso para esta area");
                context.status(401);

            }

        });

    }


}
