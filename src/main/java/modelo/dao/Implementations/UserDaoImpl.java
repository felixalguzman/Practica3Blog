package modelo.dao.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import encapsulacion.User;
import modelo.dao.interfaces.UserDao;
import modelo.servicios.Utils.DBService;

public class UserDaoImpl implements UserDao {



    @Override
    public void insert(User e){
        Connection con = null;
    
    try	{

        con = DBService.getInstance().getConnection();
        String sql = "Insert into PUBLIC.USUARIO (id, username, nombre, pass, administrador, autor) values(NEXTVAL('SECUENCIA_USUARIO'), ?, ?, ?, ?, ?); ";
        PreparedStatement preparedStatement = con.prepareStatement(sql);

//        preparedStatement.setLong(1, e.getId());
        preparedStatement.setString(1, e.getUsername());
        preparedStatement.setString(2, e.getNombre());
        preparedStatement.setString(3, e.getPassword());
        preparedStatement.setBoolean(4, e.getAdministrator());
        preparedStatement.setBoolean(5, e.getAutor());


        preparedStatement.execute();
       

        } catch (Exception e1) {
         Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                assert con != null;
                con.close();
			} catch (SQLException e1) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
	}

    @Override
    public void update(User e) {

        Connection con = null;
        try {

            con = DBService.getInstance().getConnection();
            String sql = "UPDATE PUBLIC.USUARIO u SET id = ?, USERNAME = ?, NOMBRE= ?,  PASS = ?, ADMINISTRADOR = ?, AUTOR = ? WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(2, e.getUsername());
            preparedStatement.setString(3, e.getNombre());
            preparedStatement.setString(4, e.getPassword());
            preparedStatement.setBoolean(5, e.getAdministrator());
            preparedStatement.setBoolean(6, e.getAutor());
            preparedStatement.setLong(7, e.getId());

            preparedStatement.executeUpdate();

        }catch (Exception e1){
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public void delete(User e) {
        Connection con = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "delete from PUBLIC.USUARIO where id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.USUARIO";
            con = DBService.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setNombre(resultSet.getString("nombre"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("pass"));
                user.setAdministrator(resultSet.getBoolean("administrador"));
                user.setAutor(resultSet.getBoolean("autor"));
                
                list.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally{
            try {
                assert con != null;
                con.close();
			} catch (SQLException e) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }

    @Override
    public User getById(long id) {

        Connection con = null;
        User user = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.USUARIO u WHERE u.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setNombre(resultSet.getString("nombre"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("pass"));
                user.setAdministrator(resultSet.getBoolean("administrador"));
                user.setAutor(resultSet.getBoolean("autor"));
            }

        }catch (Exception e){
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return user;
    }

    @Override
    public User validateLogIn(String user, String pass) {


        User usuario = null;

        Connection con = null;
        try {

            String query = "SELECT * FROM USUARIO u WHERE u.USERNAME = ? AND u.PASS = ?";
            con = DBService.getInstance().getConnection();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, user);
            prepareStatement.setString(2, pass);

            ResultSet resultSet = prepareStatement.executeQuery();


            while (resultSet.next()){

                usuario = new User();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("pass"));
                usuario.setAdministrator(resultSet.getBoolean("administrador"));
                usuario.setAutor(resultSet.getBoolean("autor"));
            }



        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                assert con != null;
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usuario;
    }


}
