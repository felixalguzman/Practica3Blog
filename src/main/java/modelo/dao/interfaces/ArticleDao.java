package modelo.dao.interfaces;

import encapsulacion.Article;

import java.util.List;

public interface ArticleDao {

    void insert(Article e);

    void update(Article e);

    long getNextID();

    void delete(Article e);

    List<Article> getAll();

    List<Article> getByAuthor(long id);

    Article getById(long id);
}
