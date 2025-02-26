package modelo.dao.interfaces;

import encapsulacion.Tag;

import java.util.List;

public interface TagDao {

    void insert(Tag e);

    void update(Tag e);

    void delete(Tag e);

    List<Tag> getAll();

    Tag getById(long id);

    /**
     * Función para obtener las etiquetas de un artículo.
     * @param id del articulo
     * @return lista de etiquetas.
     */
    List<Tag> getArticleById(long id);
}
