package modelo.servicios.EntityServices;

import encapsulacion.Tag;
import modelo.dao.interfaces.TagDao;
import modelo.dao.Implementations.TagDaoImpl;

import java.util.List;

public class TagService implements TagDao {
    private final TagDaoImpl tagImplementation;

    public TagService() {
        tagImplementation = new TagDaoImpl();
    }

    @Override
    public void insert(Tag e) {
        tagImplementation.insert(e);
    }

    @Override
    public void update(Tag e) {
        tagImplementation.update(e);
    }

    @Override
    public void delete(Tag e) {
        tagImplementation.delete(e);
    }

    @Override
    public List<Tag> getAll() {
        return tagImplementation.getAll();
    }

    @Override
    public Tag getById(long id) {
        return tagImplementation.getById(id);
    }

    @Override
    public List<Tag> getArticleById(long id) {
        return tagImplementation.getArticleById(id);
    }
}
