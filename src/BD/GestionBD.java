/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hector Pose Carames
 */
public class GestionBD {

    public static boolean createNewTable() {
        Boolean conex = false;
        // url = ruta de la base de datos
        String url = "jdbc:sqlite:/home/menuven/BD/discos.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS interprete (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	nombre text NOT NULL, \n"
                + "	genero text NOT NULL, \n"
                + "     image BLOB \n"
                + " );";
        String sql2 = "CREATE TABLE IF NOT EXISTS discos (\n"
                + "	codigo integer PRIMARY KEY,\n"
                + "     id integer FOREIGN KEY, \n"
                + "	nombre text NOT NULL, \n"
                + "     image BLOB \n"
                + " );";
        String sql3 = "CREATE TABLE IF NOT EXISTS canciones (\n"
                + "	id integer PRIMARY KEY,\n"
                + "     codigo integer FOREIGN KEY, \n"
                + "	nombre text NOT NULL, \n"
                + "     image BLOB \n"
                + " );";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // creamos la tabla cargando nuestra sentencia en la variable sql
            stmt.execute(sql);
            conex = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conex;
    }

    public void insertI(String nombre, String genero, String ruta) throws FileNotFoundException, IOException {
        String sql = "insert into interprete(nombre,genero,image) values(?,?,?)";
        Connection conn = GeneralBD.connect();
        FileInputStream fi = null;
        PreparedStatement pstmt = null;

        try {

            File file = new File(ruta);
            fi = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int i; (i = fi.read(buf)) != -1;) {
                bos.write(buf, 0, i);
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setBytes(3, bos.toByteArray());
            pstmt.executeUpdate();
            System.out.println("yep");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public ArrayList<Object[]> tablas() {
        FileOutputStream fos = null;
        ArrayList<Object[]> tablas = new ArrayList<>();
        String sql = "SELECT id, nombre, genero, image FROM interprete";

        try (Connection conn = GeneralBD.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                File file = new File("/home/menuven/NuevaI/Imagen.jpg");
                file.createNewFile();
                fos = new FileOutputStream(file);
                InputStream input = rs.getBinaryStream("image");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
                Object[] datos = new Object[4];
                datos[0] = Integer.toString(rs.getInt("id"));
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("genero");
                datos[3] = file;

                tablas.add(datos);
            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tablas;
    }
}
