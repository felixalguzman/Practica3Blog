package main;

import encapsulacion.Article;
import encapsulacion.Comment;
import encapsulacion.Tag;
import encapsulacion.User;
import io.javalin.Javalin;
import io.javalin.http.Cookie;
import io.javalin.http.staticfiles.Location;
import modelo.servicios.EntityServices.ArticleService;
import modelo.servicios.EntityServices.CommentService;
import modelo.servicios.EntityServices.TagService;
import modelo.servicios.EntityServices.UserService;
import modelo.servicios.Utils.BootStrapService;
import modelo.servicios.Utils.Crypto;
import modelo.servicios.Utils.Filters;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static User user;
    static final String iv = "0123456789123456"; // This has to be 16 characters
    static final String secretKeyUSer = "qwerty987654321";
    static final String secretKeyContra = "123456789klk";

    public static void main(String[] args) throws SQLException {


        // Start Javalin
        var app = Javalin.create(config -> {
            config.staticFiles.add("/templates", Location.CLASSPATH); // Serve static files
            config.fileRenderer(new JavalinThymeleaf());
        }).start(7070);

        // Initialize services
        BootStrapService.startDb();
        BootStrapService.createTables();
        UserService userService = new UserService();
        ArticleService articleService = new ArticleService();
        TagService tagService = new TagService();
        CommentService commentService = new CommentService();

        new Filters(app);
        // Routes
        app.get("/", ctx -> {
            Map<String, Object> attributes = new HashMap<>();
            Map<String, String> cookies = ctx.cookieMap();

            String[] llaveValor = new String[2];
            System.out.println("Cookie value: " + cookies.get("login"));

            for (String key : cookies.keySet()) {
                System.out.println("llave: " + key + " valor: " + cookies.get(key));
                llaveValor = cookies.get(key).split(",");
            }

            if (llaveValor.length > 1) {
                Crypto crypto = new Crypto();

                String userDecrypted = crypto.decrypt(llaveValor[0], iv, secretKeyUSer);
                String contraDecrypted = crypto.decrypt(llaveValor[1], iv, secretKeyContra);

                User user1 = userService.validateLogIn(userDecrypted, contraDecrypted);
                if (user1 != null) {
                    Main.user = user1;
                    ctx.sessionAttribute("usuario", Main.user);
                    System.out.println("User found");
                    ctx.redirect("/inicio");
                    return;
                } else {
                    System.out.println("User not found");
                }
            }
            ctx.render("/templates/login.html", attributes);
        });

        app.get("/inicio", ctx -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Inicio");
            attributes.put("etiquetas", tagService.getAll());
            attributes.put("list", articleService.getAll());
            attributes.put("usuario", user);

            ctx.render("/templates/inicio.html", attributes);
        });

        app.get("/verMas/{id}", ctx -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = ctx.pathParam("id");

            Article article = articleService.getById(Integer.parseInt(idArticulo));
            attributes.put("articulo", article);
            attributes.put("comentarios", commentService.getArticleById(Integer.parseInt(idArticulo)));
            attributes.put("etiquetas", tagService.getArticleById(Integer.parseInt(idArticulo)));
            attributes.put("usuario", user);

            ctx.render("/templates/post.html", attributes);
        });

        app.post("/agregarComentario", ctx -> {
            if (user == null) {
                ctx.redirect("/errorPost/401");
                return;
            }

            String comentario = ctx.formParam("comentario");
            String articulo = ctx.formParam("articulo");

            Article article = articleService.getById(Integer.parseInt(articulo));
            commentService.insert(new Comment(comentario, user, article));

            ctx.redirect("/verMas/" + articulo);
        });

        app.get("/agregarPost", ctx -> ctx.render("/templates/agregarPost.html"));

        app.get("/editarPost/{id}", ctx -> {
            Map<String, Object> attributes = new HashMap<>();
            String idArticulo = ctx.pathParam("id");
            long articleId = Long.parseLong(idArticulo);

            if (user != null) {
                List<Article> articles = articleService.getByAuthor(user.getId());
                if (articleService.searchByPost(articles, articleId) || user.getAdministrator()) {
                    Article article = articleService.getById(articleId);
                    attributes.put("articulo", article);

                    List<Tag> tags = tagService.getArticleById(articleId);
                    StringBuilder tagString = new StringBuilder();
                    for (int i = 0; i < tags.size(); i++) {
                        if (i == tags.size() - 1) {
                            tagString.append(tags.get(i).getEtiqueta());
                        } else {
                            tagString.append(tags.get(i).getEtiqueta()).append(",");
                        }
                    }
                    attributes.put("etiquetas", tagString.toString());
                } else {
                    ctx.redirect("/errorPost/401");
                    return;
                }
            } else {
                ctx.redirect("/errorPost/401");
                return;
            }

            ctx.render("/templates/editarPost.html", attributes);
        });

        app.post("/guardarPost", ctx -> {
            User author = user;
            String titulo = ctx.formParam("titulo");
            String cuerpo = ctx.formParam("cuerpo");
            long now = System.currentTimeMillis();
            java.sql.Date nowsql = new java.sql.Date(now);

            String etiquetas = ctx.formParam("etiquetas");
            String[] tagsArray = etiquetas.split(",");
            long articleId = articleService.getNextID();

            Article article = new Article(titulo, cuerpo, author, nowsql);
            articleService.insert(article);

            for (String tag : tagsArray) {
                Tag newTag = new Tag(tag, articleService.getById(articleId));
                tagService.insert(newTag);
            }

            ctx.redirect("/inicio");
        });

        app.post("/iniciarSesion", ctx -> {
            String username = ctx.formParam("usuario");
            String password = ctx.formParam("password");
            String remember = ctx.formParam("remember");

            User user1 = userService.validateLogIn(username, password);

            if (user1 != null) {
                if ("on".equalsIgnoreCase(remember)) {
                    Crypto crypto = new Crypto();
                    String userEncrypt = crypto.encrypt(username, iv, secretKeyUSer);
                    String passwordEncrypt = crypto.encrypt(password, iv, secretKeyContra);

                    var value = userEncrypt + "," + passwordEncrypt;
                    var cookie = new Cookie("login", value, "/", 604800);
                    ctx.cookie(cookie);
                }
                Main.user = user1;
                ctx.sessionAttribute("usuario", Main.user);
                ctx.redirect("/inicio");
            } else {
                ctx.redirect("/inicio");
            }
        });

        app.get("/logOut", ctx -> {
            user = null;
            ctx.sessionAttribute("usuario", null);
            ctx.removeCookie("login");
            ctx.redirect("/inicio");
        });

        app.get("/errorPost/{error}", ctx -> {
            Map<String, Object> attributes = new HashMap<>();
            String error = ctx.pathParam("error");

            attributes.put("primero", error.substring(0, 1));
            attributes.put("segundo", error.substring(1, 2));
            attributes.put("tercero", error.substring(2, 3));
            attributes.put("mensaje", "Usted no tiene permisos para esta area!");

            ctx.render("/templates/error.html", attributes);
        });
    }
}
