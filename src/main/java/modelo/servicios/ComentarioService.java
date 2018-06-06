package modelo.servicios;

import encapsulacion.Comentario;
import modelo.dao.ComentarioDAO;
import modelo.dao.ComentarioDAOImpl;

import java.util.List;

public class ComentarioService implements ComentarioDAO {
    public ComentarioDAOImpl comentarioDAO;

    public ComentarioService() {
        comentarioDAO = new ComentarioDAOImpl();
    }

    @Override
    public void insert(Comentario e) {
        comentarioDAO.insert(e);

    }

    @Override
    public void update(Comentario e) {
        comentarioDAO.update(e);
    }

    @Override
    public void delete(Comentario e) {
        comentarioDAO.delete(e);
    }

    @Override
    public List<Comentario> getAll() {
        return comentarioDAO.getAll();
    }

    @Override
    public Comentario getById(long id) {
        return comentarioDAO.getById(id);
    }

    @Override
    public List<Comentario> getByArticulo(long id) {
        return comentarioDAO.getByArticulo(id);
    }
}
