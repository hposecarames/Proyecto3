/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import static BD.GeneralBD.createNewDatabase;
import static BD.GestionBD.createNewTable;

/**
 *
 * @author Hector Pose Carames
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase("discos.db");
        createNewTable();
        Principal pri = new Principal();
        pri.setVisible(true);
    }
    
}
