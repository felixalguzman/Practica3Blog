package main;

import modelo.servicios.BootStrapService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        BootStrapService.startDb();

        BootStrapService.crearTablas();


    }
}
