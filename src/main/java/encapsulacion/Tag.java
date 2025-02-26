package encapsulacion;

public class Tag {

    private long id;
    private String etiqueta;
    private Article article;

    public Tag() {
    }

    public Tag(long id, String etiqueta, Article article) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.article = article;
    }
    public Tag(String etiqueta, Article article) {
        this.etiqueta = etiqueta;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Article getArticulo() {
        return article;
    }

    public void setArticulo(Article article) {
        this.article = article;
    }
}
