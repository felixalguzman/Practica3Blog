package encapsulacion;

public class Usuario {

    private long id;
    private String username;
    private String nombre;
    private String password;
    private Boolean administrator;
    private Boolean autor;

    public Usuario() {
    }

    public Usuario(long id, String username, String nombre, String password, Boolean administrator, Boolean autor) {
        this.setId(id);
        this.setUsername(username);
        this.setNombre(nombre);
        this.setPassword(password);
        this.setAdministrator(administrator);
        this.setAutor(autor);
    }

    public Usuario(String username, String nombre, String password, Boolean administrator, Boolean autor) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrator = administrator;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public Boolean getAutor() {
        return autor;
    }

    public void setAutor(Boolean autor) {
        this.autor = autor;
    }
}
