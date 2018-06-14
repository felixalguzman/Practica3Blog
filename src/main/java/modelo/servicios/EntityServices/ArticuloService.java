package modelo.servicios.EntityServices;

import encapsulacion.Articulo;
import modelo.dao.interfaces.ArticuloDAO;
import modelo.dao.Implementations.ArticuloDAOImpl;

import java.util.List;

public class ArticuloService implements ArticuloDAO {
    private ArticuloDAOImpl articuloDAO;

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
    public long getNextID() {
        return articuloDAO.getNextID();
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

    @Override
    public List<Articulo> getbyAutor(long id) {
        return articuloDAO.getbyAutor(id);
    }


    public boolean buscarPost(List<Articulo> articulos, long id){

        for (Articulo articulo:articulos) {
            if (articulo.getId() == id)
                return true;
        }

        return false;
    }

}
