package modelo.dao.Implementations;

import encapsulacion.Comment;
import encapsulacion.User;
import modelo.dao.interfaces.CommentDao;
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

public class CommentDaoImpl implements CommentDao {

    @Override
    public void insert(Comment e) {
        Connection con = null;

        try	{

            con = DBService.getInstance().getConnection();
            String sql = "Insert into PUBLIC.COMENTARIO(id, COMENTARIO, AUTOR, ARTICULO) values(nextval('SECUENCIA_COMENTARIO'), ?, ?, ?); ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

//            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(1, e.getComentario());
            preparedStatement.setLong(2, e.getAutor().getId());
            preparedStatement.setLong(3, e.getArticulo().getId());
            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
    }

    @Override
    public void update(Comment e) {
        Connection con = null;
        try {

            con = DBService.getInstance().getConnection();
            String sql = "UPDATE PUBLIC.COMENTARIO u SET COMENTARIO = ?, AUTOR = ?, ARTICULO = ? WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
           
            preparedStatement.setString(1, e.getComentario());
            preparedStatement.setLong(2, e.getAutor().getId());
            preparedStatement.setLong(3, e.getArticulo().getId());
            preparedStatement.setLong(4, e.getId());

            preparedStatement.executeUpdate();

        }catch (Exception e1){
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public void delete(Comment e) {
        Connection con = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "delete from PUBLIC.COMENTARIO where id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e1);

            }
        }

    }

    @Override
    public List<Comment> getAll() {
        List<Comment> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.COMENTARIO";
            con = DBService.getInstance().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComentario(resultSet.getString("comentario"));

                list.add(comment);
            }
        } catch (Exception e) {
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally{
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }


        return list;
    }

    @Override
    public Comment getById(long id) {
        Connection con = null;
        Comment comment = null;

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.COMENTARIO u WHERE u.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComentario(resultSet.getString("comentario"));
            }

        }catch (Exception e){
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return comment;
    }

    @Override
    public List<Comment> getArticleById(long id) {
        Connection con = null;
        List<Comment> list = new ArrayList<>();

        try {
            con = DBService.getInstance().getConnection();
            String sql = "SELECT * FROM PUBLIC.COMENTARIO  WHERE ARTICULO = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Comment comment = new Comment();
                comment.setId(resultSet.getLong("ID"));
                comment.setComentario(resultSet.getString("comentario"));
                UserService userService = new UserService();
                User user = userService.getById(resultSet.getLong("autor"));
                comment.setAutor(user);
                list.add(comment);
            }

        }catch (Exception e){
            Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(CommentDaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return list;
    }
}
