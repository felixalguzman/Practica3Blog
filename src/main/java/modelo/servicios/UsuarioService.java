package modelo.servicios;

import encapsulacion.Usuario;
import modelo.dao.UsuarioDAO;
import modelo.dao.UsuarioDAOImpl;

import java.util.List;

public class UsuarioService implements UsuarioDAO {

    private UsuarioDAOImpl usuarioDAO;

    public UsuarioService(){
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public void insert(Usuario e) {
        usuarioDAO.insert(e);
    }

    @Override
    public void update(Usuario e) {
        usuarioDAO.update(e);
    }

    @Override
    public void delete(Usuario e) {
        usuarioDAO.delete(e);
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioDAO.getAll();
    }

    @Override
    public Usuario getById(long id) {
        return usuarioDAO.getById(id);
    }
}
