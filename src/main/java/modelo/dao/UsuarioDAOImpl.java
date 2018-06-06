package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import encapsulacion.Usuario;
import modelo.servicios.DBService;

public class UsuarioDAOImpl implements UsuarioDAO {



    @Override
    public void insert(Usuario e){
        Connection con = null;
    
    try	{

        con = DBService.getInstancia().connection();
        String sql = "Insert into PUBLIC.USUARIO (id, username, nombre, pass, administrador, autor) values(?, ?, ?, ?, ?, ?); ";
        PreparedStatement preparedStatement = con.prepareStatement(sql);

        preparedStatement.setLong(1, e.getId());
        preparedStatement.setString(2, e.getUsername());
        preparedStatement.setString(3, e.getNombre());
        preparedStatement.setString(4, e.getPassword());
        preparedStatement.setBoolean(5, e.getAdministrator());
        preparedStatement.setBoolean(6, e.getAutor());


        preparedStatement.execute();
       

        } catch (Exception e1) {
         Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
				con.close();
			} catch (SQLException e1) {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
	}

    @Override
    public void update(Usuario e) {

        Connection con = null;
        try {

            con = DBService.getInstancia().connection();
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
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public void delete(Usuario e) {
        Connection con = null;

        try {
            con = DBService.getInstancia().connection();
            String sql = "delete from PUBLIC.USUARIO u where u.id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.USUARIO";
            con = DBService.getInstancia().connection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("pass"));
                usuario.setAdministrator(resultSet.getBoolean("administrador"));
                usuario.setAutor(resultSet.getBoolean("autor"));
                
                list.add(usuario);
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally{
            try {
				con.close();
			} catch (SQLException e) {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }

    @Override
    public Usuario getById(long id) {

        Connection con = null;
        Usuario usuario = null;

        try {
            con = DBService.getInstancia().connection();
            String sql = "SELECT * FROM PUBLIC.USUARIO u WHERE u.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("pass"));
                usuario.setAdministrator(resultSet.getBoolean("administrador"));
                usuario.setAutor(resultSet.getBoolean("autor"));
            }

        }catch (Exception e){
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return usuario;
    }
}
