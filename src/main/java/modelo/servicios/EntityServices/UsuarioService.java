package modelo.servicios.EntityServices;

import encapsulacion.Usuario;
import modelo.dao.interfaces.UsuarioDAO;
import modelo.dao.Implementations.UsuarioDAOImpl;

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

    @Override
    public Usuario validateLogIn(String user, String pass) {
        return usuarioDAO.validateLogIn(user, pass);
    }


}
