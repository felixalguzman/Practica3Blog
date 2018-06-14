<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Post - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/blog-post.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
          <a class="navbar-brand" href="/inicio">Inicio</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                  aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                  <li class="nav-item">
                      <a class="nav-link" href="/agregarPost">Agregar Post</a>
                  </li>

                  <li class="nav-item">
                      <a class="nav-link" href="/agregarUsuario">Agregar Usuario</a>
                  </li>

                    <#if (usuario??)>
                         <li class="nav-item">
                             <a class="nav-link" href="/logOut">Salir</a>
                         </li>
                    <#else>
                     <li class="nav-item">
                         <a class="nav-link" href="/">Log In</a>
                     </li>
                    </#if>



              </ul>
          </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">

          <!-- Title -->
          <h1 class="mt-4">${articulo.titulo}</h1>

          <!-- Author -->
          <p class="lead">
            Por
            <a href="#">${articulo.autor.nombre}</a>
          </p>

          <hr>

          <!-- Post Content -->
          <p class="lead">${articulo.cuerpo}</p>

          <!-- Date/Time -->
          <p>Publicado el ${articulo.fecha}</p>
            <div>
                <a href="/editarPost/${articulo.id}" class="btn btn-primary">Editar Articulo</a>
                <a href="/eliminarPost/${articulo.autor.id}/${articulo.id}" class="btn btn-primary">Eliminar Articulo</a>
            </div>



          <hr>

          <!-- Comments Form -->
          <div class="card my-4">
            <h5 class="card-header">Deja un comentario:</h5>
            <div class="card-body">
              <form method="post" action="/agregarComentario">
                <div class="form-group">
                    <textarea class="form-control" rows="3" name="comentario"></textarea>
                    <input type="hidden" name="articulo" value="${articulo.id}">
                    <input type="hidden" name="autor" value="${articulo.autor.id}">
                </div>
                <button style="float: right" type="submit" class="btn btn-primary">Guardar</button>
              </form>
            </div>
          </div>


          <#list comentarios as comentario>
                <!-- Single Comment -->
          <div class="media mb-4">
              <div class="media-body">
                  <h5 class="mt-0">${comentario.autor.nombre}</h5>
                  ${comentario.comentario}
              </div>
          </div>
          </#list>


        </div>
        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Categories Widget -->

          <div class="card my-4">
            <h5 class="card-header">Etiquetas</h5>
            <ul>
                <#list etiquetas as etiqueta>
                    <li><span class="badge badge-primary">${etiqueta.etiqueta}</span></li>
                </#list>
            </ul>
          </div>


        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
