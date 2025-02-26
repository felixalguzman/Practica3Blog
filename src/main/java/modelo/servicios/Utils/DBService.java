package modelo.servicios.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static DBService instance;

    public static DBService getInstance() {

        if (instance == null)
            instance = new DBService();
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            String url = "jdbc:h2:~/blog";
            connection = DriverManager.getConnection(url, "", "");
        } catch (Exception e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return connection;
    }
}
