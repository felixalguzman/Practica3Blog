package modelo.servicios.EntityServices;

import encapsulacion.Articulo;
import modelo.dao.interfaces.ArticuloDAO;
import modelo.dao.Implementations.ArticuloDAOImpl;

import java.util.List;

public class ArticuloService implements ArticuloDAO {
    public ArticuloDAOImpl articuloDAO;

    public ArticuloService(){
        articuloDAO = new ArticuloDAOImpl();
    }

    @Override
    public void insert(Articulo e) {
        articuloDAO.insert(e);
    }

    @Override
    public void update(Articulo e) {
        articuloDAO.update(e);
    }

    @Override
    public void delete(Articulo e) {
        articuloDAO.delete(e);
    }

    @Override
    public List<Articulo> getAll() {
        return articuloDAO.getAll();
    }

    @Override
    public Articulo getById(long id) {
        return articuloDAO.getById(id);
    }
}
