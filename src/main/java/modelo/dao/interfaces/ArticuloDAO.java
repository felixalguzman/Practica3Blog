package modelo.dao.interfaces;

import encapsulacion.Articulo;

import java.util.List;

public interface ArticuloDAO {

    void insert(Articulo e);

    void update(Articulo e);

    long getNextID();

    void delete(Articulo e);

    List<Articulo> getAll();

    List<Articulo> getbyAutor(long id);

    Articulo getById(long id);
}
