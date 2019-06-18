/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hector Pose Carames
 */
public class Imagenes extends DefaultTableCellRenderer {

    public Imagenes() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column < 3) {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        } else {
            JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column));
            ImageIcon icon = new ImageIcon((String) value);
            lbl = new JLabel(icon);
            lbl.setSize(icon.getIconWidth(), icon.getIconHeight());
            return lbl;
        }

    }

}
