package modelo.dao.Implementations;

import encapsulacion.Article;
import encapsulacion.User;
import modelo.dao.interfaces.ArticleDao;
import modelo.servicios.EntityServices.TagService;
import modelo.servicios.EntityServices.UserService;
import modelo.servicios.Utils.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleDaoImpl implements ArticleDao {

    @Override
    public void insert(Article e) {
        Connection con = null;

        try {

            con = DBService.getInstance().getConnection();
            String sql = "Insert into PUBLIC.ARTICULO(id, TITULO, CUERPO, AUTOR, FECHA) values(NEXTVAL('SECUENCIA_ARTICULO'), ?, ?, ?, ?); ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            //preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(1, e.getTitulo());
            preparedStatement.setString(2, e.getCuerpo());
            preparedStatement.setLong(3, e.getAutor().getId());
            preparedStatement.setDate(4, e.getFecha());

            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e1) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
    }

    @Override
    public void update(Article e) {

        Connection con = null;
        try {

            con = DBService.getInstance().getConnection();
            String sql = "UPDATE PUBLIC.ARTICULO u SET id = ?, TITULO = ?, CUERPO = ?, FECHA = ?  WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(2, e.getTitulo());
            preparedStatement.setString(3, e.getCuerpo());
            preparedStatement.setDate(4, e.getFecha());
            preparedStatement.setLong(5, e.getId());

            preparedStatement.executeUpdate();

        } catch (Exception e1) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        } finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public long getNextID() {
        Long next = null;
        Connection con = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT CURRVAL('PUBLIC.SECUENCIA_ARTICULO')";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                next = resultSet.getLong(1);
            }
        } catch (Exception e1) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        } finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
            next = 1L;
        }
        return next + 1;
    }

    @Override
    public void delete(Article e) {
        Connection con = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "delete from PUBLIC.ARTICULO u where u.id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        } catch (Exception e1) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

        } finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<Article> getAll() {
        List<Article> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ARTICULO ORDER BY FECHA DESC ";
            con = DBService.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Article article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setTitulo(resultSet.getString("Titulo"));
                article.setCuerpo(resultSet.getString("Cuerpo"));
                UserService userService = new UserService();
                User user = userService.getById(resultSet.getLong("autor"));
                article.setAutor(user);
                article.setFecha(resultSet.getDate("fecha"));


                list.add(article);
            }
        } catch (Exception e) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }

    @Override
    public List<Article> getByAuthor(long id) {
        List<Article> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ARTICULO WHERE AUTOR = ? ORDER BY FECHA DESC  ";
            con = DBService.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Article article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setTitulo(resultSet.getString("Titulo"));
                article.setCuerpo(resultSet.getString("Cuerpo"));

                UserService userService = new UserService();
                User user = userService.getById(resultSet.getLong("autor"));
                article.setAutor(user);
                article.setFecha(resultSet.getDate("fecha"));


                list.add(article);
            }
        } catch (Exception e) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }


    @Override
    public Article getById(long id) {

        Connection con = null;
        Article article = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.ARTICULO a WHERE a.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setTitulo(resultSet.getString("titulo"));
                article.setCuerpo(resultSet.getString("cuerpo"));

                UserService userService = new UserService();
                User user = userService.getById(resultSet.getLong("autor"));
                article.setAutor(user);

                article.setFecha(resultSet.getDate("fecha"));
                TagService tagService = new TagService();

            }

        } catch (Exception e) {
            Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticleDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return article;
    }


}
