package modelo.dao.Implementations;

import encapsulacion.Articulo;
import modelo.dao.interfaces.ArticuloDAO;
import modelo.servicios.Utils.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticuloDAOImpl implements ArticuloDAO {

    @Override
    public void insert(Articulo e){
        Connection con = null;

        try	{

            con = DBService.getInstancia().connection();
            String sql = "Insert into PUBLIC.ARTICULO(id, TITULO, CUERPO, AUTOR, FECHA) values(?, ?, ?, ?, ?); ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(2, e.getTitulo());
            preparedStatement.setString(3, e.getCuerpo());
            preparedStatement.setLong(4, e.getAutor().getId());
            preparedStatement.setDate(5, e.getFecha());

            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
    }

    @Override
    public void update(Articulo e) {

        Connection con = null;
        try {

            con = DBService.getInstancia().connection();
            String sql = "UPDATE PUBLIC.ETIQUETA u SET id = ?, ETIQUETA= ? WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            //preparedStatement.setString(2, e.getArticulo());
            preparedStatement.setLong(3, e.getId());

            preparedStatement.executeUpdate();

        }catch (Exception e1){
            Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public void delete(Articulo e) {
        Connection con = null;

        try {
            con = DBService.getInstancia().connection();
            String sql = "delete from PUBLIC.ARTICULO u where u.id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<Articulo> getAll() {
        List<Articulo> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ARTICULO";
            con = DBService.getInstancia().connection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Articulo etiqueta = new Articulo();
                etiqueta.setId(resultSet.getLong("id"));
               // etiqueta.setArticulo(resultSet.getString("etiqueta"));

                list.add(etiqueta);
            }
        } catch (Exception e) {
            Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }

    @Override
    public Articulo getById(long id) {

        Connection con = null;
        Articulo articulo = null;

        try {
            con = DBService.getInstancia().connection();
            String sql = "SELECT * FROM PUBLIC.ARTICULO a, PUBLIC.USUARIO u WHERE a.ID = ? AND a.AUTOR = u.ID";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){


                articulo = new Articulo();
                articulo.setId(resultSet.getLong("id"));
                articulo.setTitulo(resultSet.getString("titulo"));
                articulo.setCuerpo(resultSet.getString("cuerpo"));
                //articulo.setAutor(resultSet.getString("titulo"));
            }

        }catch (Exception e){
            Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticuloDAOImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return articulo;
    }
}
