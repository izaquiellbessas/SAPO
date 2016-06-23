/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.util;


import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import sapo.Main;
import sapo.conexao.Conectar;


/**
 *
 * @author wsouza
 */
public class TabelaPadrao {

    public TabelaPadrao() {
    }



    public DefaultTableModel retornaTableModel(String sql) throws SQLException {
        Conectar conexao = Main.getCon();
        //Setando a conexão
        Connection conn = conexao.getConn();
        Statement rsSql = conn.createStatement();
        rsSql.execute(sql);
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSetMetaData metaData = rsSql.getResultSet().getMetaData();

        for (int i = 1; i != metaData.getColumnCount() + 1; i++) {
            modelo.addColumn(metaData.getColumnLabel(i));
        }

        while (rsSql.getResultSet().next()) {
            Vector vetor = new Vector();
            for (int i = 1; i != metaData.getColumnCount() + 1; i++) {
                if (rsSql.getResultSet().getObject(i) instanceof String) {
                    vetor.add(rsSql.getResultSet().getString(i));
                } else if (rsSql.getResultSet().getObject(i) instanceof Integer) {
                    vetor.add(rsSql.getResultSet().getInt(i));
                } else if (rsSql.getResultSet().getObject(i) instanceof Double) {
                    vetor.add(rsSql.getResultSet().getDouble(i));
                } else {
                    vetor.add(rsSql.getResultSet().getObject(i));
                }
            }

            modelo.addRow(vetor);

        }
        return modelo;
    }
}