package modelo.dao.interfaces;

import encapsulacion.Comment;

import java.util.List;

public interface CommentDao {

    void insert(Comment e);

    void update(Comment e);

    void delete(Comment e);

    List<Comment> getAll();

    Comment getById(long id);

    /**
     * Función para obtener los comentarios de un artículo.
     * @param id del articulo
     * @return lista de comentarios.
     */
    List<Comment> getArticleById(long id);
}
