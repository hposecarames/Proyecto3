/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hector Pose Carames
 */
public class GeneralBD {
    public static boolean createNewDatabase(String fileName) {
        Boolean conex = false;
        String url = "jdbc:sqlite:/home/menuven/BD/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                conex = true;
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver " + meta.getDriverName());
                System.out.println("Base de datos creada.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conex;
    }
     public static Connection connect() {
        // url = ruta de nuestra base de datos
        String url = "jdbc:sqlite:/home/menuven/BD/discos.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
