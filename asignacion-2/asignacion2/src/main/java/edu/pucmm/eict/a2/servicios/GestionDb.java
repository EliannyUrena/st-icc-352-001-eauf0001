package edu.pucmm.eict.a2.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionDb {

    private static String url = "jdbc:h2:tcp://localhost:9092/~/dbEstudiante";
    private static String usuario = "sa";
    private static String contrasena = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}
