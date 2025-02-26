package modelo.servicios.EntityServices;

import encapsulacion.Comment;
import modelo.dao.interfaces.CommentDao;
import modelo.dao.Implementations.CommentDaoImpl;

import java.util.List;

public class CommentService implements CommentDao {
    private final CommentDaoImpl commentImplementation;

    public CommentService() {
        commentImplementation = new CommentDaoImpl();
    }

    @Override
    public void insert(Comment e) {
        commentImplementation.insert(e);

    }

    @Override
    public void update(Comment e) {
        commentImplementation.update(e);
    }

    @Override
    public void delete(Comment e) {
        commentImplementation.delete(e);
    }

    @Override
    public List<Comment> getAll() {
        return commentImplementation.getAll();
    }

    @Override
    public Comment getById(long id) {
        return commentImplementation.getById(id);
    }

    @Override
    public List<Comment> getArticleById(long id) {
        return commentImplementation.getArticleById(id);
    }
}
