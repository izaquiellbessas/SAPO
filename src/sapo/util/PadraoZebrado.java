/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author wsouza
 */
public class PadraoZebrado implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component linha = table.getDefaultRenderer(String.class).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected)
            linha.setBackground(table.getSelectionBackground());
        else{
            if ((row % 2) == 0)
                linha.setBackground(Color.LIGHT_GRAY);
            else
                linha.setBackground(Color.WHITE);
        }
        return linha;
    }
}
