package modelo.dao.interfaces;

import encapsulacion.User;

import java.util.List;

public interface UserDao {

    void insert(User e);

    void update(User e);

    void delete(User e);

    List<User> getAll();

    User getById(long id);

    User validateLogIn(String user, String pass);
}
