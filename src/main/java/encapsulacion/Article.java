package encapsulacion;

import java.sql.Date;
import java.util.List;

public class Article {

    private long id;
    private String titulo;
    private String cuerpo;
    private User autor;
    private Date fecha;
    private List<Comment> listaComments;
    private List<Tag> listaTags;

    public Article() {
    }

    public Article(String titulo, String cuerpo, User autor, Date fecha) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
    }

    public Article(long id, String titulo, String cuerpo, User autor, Date fecha) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Comment> getListaComentarios() {
        return listaComments;
    }

    public void setListaComentarios(List<Comment> listaComments) {
        this.listaComments = listaComments;
    }

    public List<Tag> getListaEtiquetas() {
        return listaTags;
    }

    public void setListaEtiquetas(List<Tag> listaTags) {
        this.listaTags = listaTags;
    }
}
