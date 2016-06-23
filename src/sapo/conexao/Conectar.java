/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author home
 */
public class Conectar {

    private Connection conn;

    public void conecte(Usuario u) {
        Usuario us = u;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(us.getUrl(), us.getUsuario(), us.getSenha());
            System.out.println("Conexão estabelecida com sucesso!!!");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Entre em contato com o seu administrador de rede, pois a conexão não pode ser estabelecida\nInformações sobre o Erro: " + cnfe.getMessage() + " sapo.conectar.Conectar: line 25");
        } catch (SQLException sqle) {
            System.out.println("Entre em contato com o seu administrador de rede, pois a conexão não pode ser estabelecida\nInformações sobre o Erro: " + sqle.getMessage() + " sapo.conectar.Conectar: line 27");
        } catch (Exception e) {
            System.out.println("Entre em contato com o seu administrador de rede, pois a conexão não pode ser estabelecida\nInformações sobre o Erro: " + e.getMessage() + " sapo.conectar.Conectar: line 29");
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void fechar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }

    public void fechar(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
    }

    public void fecharConexao() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
}
