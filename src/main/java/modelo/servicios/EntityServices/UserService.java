package modelo.servicios.EntityServices;

import encapsulacion.User;
import modelo.dao.interfaces.UserDao;
import modelo.dao.Implementations.UserDaoImpl;

import java.util.List;

public class UserService implements UserDao {

    private final UserDaoImpl userImplementation;

    public UserService(){
        userImplementation = new UserDaoImpl();
    }

    @Override
    public void insert(User e) {
        userImplementation.insert(e);
    }

    @Override
    public void update(User e) {
        userImplementation.update(e);
    }

    @Override
    public void delete(User e) {
        userImplementation.delete(e);
    }

    @Override
    public List<User> getAll() {
        return userImplementation.getAll();
    }

    @Override
    public User getById(long id) {
        return userImplementation.getById(id);
    }

    @Override
    public User validateLogIn(String user, String pass) {
        return userImplementation.validateLogIn(user, pass);
    }


}
