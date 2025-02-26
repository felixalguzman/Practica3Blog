package modelo.dao.Implementations;

import encapsulacion.Tag;
import modelo.dao.interfaces.TagDao;
import modelo.servicios.EntityServices.ArticleService;
import modelo.servicios.Utils.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TagDaoImpl implements TagDao {

    @Override
    public void insert(Tag e){
        Connection con = null;

        try	{

            con = DBService.getInstance().getConnection();
            String sql = "Insert into PUBLIC.ETIQUETA(id, ETIQUETA, ARTICULO) values(nextval('SECUENCIA_ETIQUETA') , ?, ?); ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            //preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(1, e.getEtiqueta());
            preparedStatement.setLong(2, e.getArticulo().getId());

            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
    }

    @Override
    public void update(Tag e) {

        Connection con = null;
        try {

            con = DBService.getInstance().getConnection();
            String sql = "UPDATE PUBLIC.ETIQUETA u SET id = ?, ETIQUETA= ? WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(2, e.getEtiqueta());
            preparedStatement.setLong(3, e.getId());

            preparedStatement.executeUpdate();

        }catch (Exception e1){
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public void delete(Tag e) {
        Connection con = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "delete from PUBLIC.ETIQUETA where id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<Tag> getAll() {
        List<Tag> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ETIQUETA";
            con = DBService.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tag tag = new Tag();
                ArticleService articleService = new ArticleService();
                tag.setId(resultSet.getLong("id"));
                tag.setEtiqueta(resultSet.getString("etiqueta"));
                tag.setArticulo(articleService.getById(resultSet.getLong("articulo")));
                list.add(tag);
            }
        } catch (Exception e) {
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    @Override
    public Tag getById(long id) {

        Connection con = null;
        Tag tag = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.ETIQUETA u WHERE u.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                tag = new Tag();
                tag.setId(resultSet.getLong("id"));
                tag.setEtiqueta(resultSet.getString("etiqueta"));
            }

        }catch (Exception e){
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return tag;
    }


    @Override
    public List<Tag> getArticleById(long id) {
        Connection con = null;
        List<Tag> list = new ArrayList<>();

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.ETIQUETA u WHERE u.ARTICULO = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Tag tag = new Tag();
                tag.setId(resultSet.getLong("id"));
                tag.setEtiqueta(resultSet.getString("etiqueta"));
                list.add(tag);
            }

        }catch (Exception e){
            Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(TagDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return list;
    }
}
