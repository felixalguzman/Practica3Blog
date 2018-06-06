package modelo.dao.interfaces;

import encapsulacion.Articulo;

import java.util.List;

public interface ArticuloDAO {

    void insert(Articulo e);

    void update(Articulo e);

    void delete(Articulo e);

    List<Articulo> getAll();

    Articulo getById(long id);
}
