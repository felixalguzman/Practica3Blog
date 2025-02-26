package encapsulacion;

public class Comment {

    private long id;
    private String comentario;
    private User autor;
    private Article article;

    public Comment() {
    }

    public Comment(String comentario, User autor, Article article) {
        this.comentario = comentario;
        this.autor = autor;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Article getArticulo() {
        return article;
    }

    public void setArticulo(Article article) {
        this.article = article;
    }
}
