/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author wsouza
 */
public class PadraoEditor extends JCheckBox implements TableCellRenderer {
    public PadraoEditor() {
        super();
        this.setForeground(Color.BLUE);
    }

    public void configuraComponente(JTable table, Object value, boolean isSelected, int row) {
        if (isSelected) {
            this.setBackground(table.getSelectionBackground());
            this.setSelected(true);
        } else {
            this.setSelected(false);
            if ((row % 2) == 0) {
                this.setBackground(Color.LIGHT_GRAY);
            } else {
                this.setBackground(Color.WHITE);
            }
        }
        if (value != null) {
            if (value instanceof Integer) {
                Integer valor = (Integer) value;
                this.setText(Integer.toString(valor));
            } else if (value instanceof String){
                this.setText((String)value);
            }

        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        configuraComponente(table, value, isSelected, row);
        return this;
    }
}
