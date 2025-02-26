package modelo.servicios.EntityServices;

import encapsulacion.Article;
import modelo.dao.interfaces.ArticleDao;
import modelo.dao.Implementations.ArticleDaoImpl;

import java.util.List;

public class ArticleService implements ArticleDao {
    private final ArticleDaoImpl articleImplementation;

    public ArticleService(){
        articleImplementation = new ArticleDaoImpl();
    }

    @Override
    public void insert(Article e) {
        articleImplementation.insert(e);
    }

    @Override
    public void update(Article e) {
        articleImplementation.update(e);
    }

    @Override
    public long getNextID() {
        return articleImplementation.getNextID();
    }

    @Override
    public void delete(Article e) {
        articleImplementation.delete(e);
    }

    @Override
    public List<Article> getAll() {
        return articleImplementation.getAll();
    }

    @Override
    public Article getById(long id) {
        return articleImplementation.getById(id);
    }

    @Override
    public List<Article> getByAuthor(long id) {
        return articleImplementation.getByAuthor(id);
    }


    public boolean searchByPost(List<Article> articles, long id){

        for (Article article : articles) {
            if (article.getId() == id)
                return true;
        }

        return false;
    }

}
