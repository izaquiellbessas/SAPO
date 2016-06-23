/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tiago
 */
public class TabelaAluno extends AbstractTableModel {

    private int rownum;
    private static final String[] colNames = {
        "Número Matrícula", "Nome", "dataNasc", "Classificação", "Rua","Número Rua","Cidade", "Telefone"
    };
    private ArrayList<String[]> ResultSets;
    private ResultSetMetaData metaData;

    /** Creates a new instance of TabelaAgenda */
    public TabelaAluno(ResultSet rs) throws SQLException {
        setResult(rs);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = ResultSets.get(rowIndex);
        return row[columnIndex];
    }

    public int getRowCount() {
        return ResultSets.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int param) {

        return colNames[param];
    }

    public void setResult(ResultSet rs) throws SQLException {

        ResultSets = new ArrayList<String[]>();
        while (rs.next()) {
            String[] row = {
                rs.getString("numMatricula"),
                rs.getString("nome"),
                rs.getString("dataNasc"),
                rs.getString("classificacao"),
                rs.getString("rua"),
                rs.getString("numRua"),
                rs.getString("cidade"),
                rs.getString("telefone")
            };
            ResultSets.add(row);
        }
        // notifica JTable das alterações
        fireTableStructureChanged();

    }

    public void deleteRow(int row) {
        ResultSets.remove(row);
        fireTableRowsDeleted(row, row);

    }
}
