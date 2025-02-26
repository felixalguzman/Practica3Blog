package modelo.servicios.Utils;


import encapsulacion.User;
import modelo.servicios.EntityServices.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class BootStrapService {

    public static void startDb() throws SQLException {
//        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    public static void stopDb() throws SQLException {
//        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }


    public static void createTables() throws SQLException {

        // SQL to create the USUARIO table
        String sqlUsuario = "CREATE TABLE IF NOT EXISTS USUARIO (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " + // Use AUTO_INCREMENT for ID
                "USERNAME VARCHAR(50) NOT NULL, " +
                "NOMBRE VARCHAR(50) NOT NULL, " +
                "PASS VARCHAR(50) NOT NULL, " +
                "ADMINISTRADOR BOOLEAN NOT NULL, " +
                "AUTOR BOOLEAN NOT NULL" +
                ");";

        // SQL to create the ARTICULO table
        String sqlArticulo = "CREATE TABLE IF NOT EXISTS ARTICULO (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "TITULO VARCHAR(250) NOT NULL, " +
                "CUERPO VARCHAR(250) NOT NULL, " +
                "AUTOR INTEGER NOT NULL, " +
                "FECHA DATE NOT NULL, " +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID)" +
                ");";

        // SQL to create the ETIQUETA table
        String sqlEtiqueta = "CREATE TABLE IF NOT EXISTS ETIQUETA (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "ETIQUETA VARCHAR(50) NOT NULL, " +
                "ARTICULO INTEGER NOT NULL, " +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID) " +
                "ON DELETE CASCADE" +
                ");";

        // SQL to create the COMENTARIO table
        String sqlComentario = "CREATE TABLE IF NOT EXISTS COMENTARIO (" +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "COMENTARIO VARCHAR(250) NOT NULL, " +
                "AUTOR INTEGER NOT NULL, " +
                "ARTICULO INTEGER NOT NULL, " +
                "FOREIGN KEY (AUTOR) REFERENCES USUARIO(ID), " +
                "FOREIGN KEY (ARTICULO) REFERENCES ARTICULO(ID) " +
                "ON DELETE CASCADE" +
                ");";

        // SQL to insert the admin user
        String insertAdmin = "INSERT INTO USUARIO (USERNAME, NOMBRE, PASS, ADMINISTRADOR, AUTOR) " +
                "VALUES ('admin', 'admin', 'admin', true, true);";

        String secuenciaUsuario = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_USUARIO START WITH 0 INCREMENT BY 1";
        String secuenciaArticulo = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_ARTICULO START WITH 0 INCREMENT BY 1";
        String secuenciaEtiqueta= "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_ETIQUETA START WITH 0 INCREMENT BY 1";
        String secuenciaComentario = "CREATE SEQUENCE IF NOT EXISTS SECUENCIA_COMENTARIO START WITH 0 INCREMENT BY 1";

        // Establish database connection
        try (Connection connection = DBService.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            // Create tables
            statement.execute(sqlUsuario);
            statement.execute(sqlArticulo);
            statement.execute(sqlEtiqueta);
            statement.execute(sqlComentario);

            statement.execute(secuenciaUsuario);
            statement.execute(secuenciaArticulo);
            statement.execute(secuenciaEtiqueta);
            statement.execute(secuenciaComentario);

            // Check if the admin user already exists
            UserService userService = new UserService();
            User user = userService.validateLogIn("admin", "admin");

            // Insert admin user if it doesn't exist
            if (user == null) {
                statement.execute(insertAdmin);
            }

            System.out.println("Tables and admin user created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while creating tables or inserting admin user.", e);
        }
    }


}
