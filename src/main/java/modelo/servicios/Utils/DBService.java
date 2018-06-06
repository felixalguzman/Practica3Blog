package modelo.servicios.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static DBService instancia;
    private String url = "jdbc:h2:tcp://localhost/~/blog";

    public static DBService getInstancia(){

        if (instancia == null)
                instancia = new DBService();
        return instancia;
    }

    public Connection connection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "", "");
        }catch (Exception e){
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return connection;
    }
}
