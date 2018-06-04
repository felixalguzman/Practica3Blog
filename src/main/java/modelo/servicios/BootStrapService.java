package modelo.servicios;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BootStrapService {

    public static void startDb() throws SQLException {
     //   Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    public static void crearTablas() throws SQLException {

        String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO \n"+
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "USERNAME VARCHAR(50) NOT NULL, \n" +
                "NOMBRE VARCHAR(50) NOT NULL, \n" +
                "PASS VARCHAR(50) NOT NULL, \n" +
                "ADMINISTRADOR BOOLEAN NOT NULL, \n" +
                "AUTOR BOOLEAN NOT NULL \n" +
                ");";

        String sqlEtiqueta = "CREATE TABLE IF NOT EXISTS ETIQUETA \n"+
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "ETIQUETA VARCHAR(50) NOT NULL, \n" +
                "ARTICULO INTEGER NOT NULL , \n" +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID)" +
                ");";


        String sqlComentario = "CREATE TABLE IF NOT EXISTS COMENTARIO \n"+
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "COMENTARIO VARCHAR(250) NOT NULL, \n" +
                "AUTOR INTEGER NOT NULL , \n" +
                "ARTICULO INTEGER NOT NULL , \n" +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID), \n" +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID)" +
                ");";

        String sqlArticulo = "CREATE TABLE IF NOT EXISTS ARTICULO \n"+
                "(\n" +
                "ID INTEGER PRIMARY KEY NOT NULL, \n" +
                "TITULO VARCHAR(250) NOT NULL, \n" +
                "CUERPO VARCHAR(250) NOT NULL, \n" +
                "AUTOR INTEGER NOT NULL, \n " +
                "FECHA DATE NOT NULL , \n" +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID)" +
                ");";




        Connection connection = DBService.getInstancia().connection();
        Statement statement = connection.createStatement();

        statement.execute(sqlUsuario);
        statement.execute(sqlArticulo);
        statement.execute(sqlEtiqueta);
        statement.execute(sqlComentario);

        statement.close();
        connection.close();
    }
}
