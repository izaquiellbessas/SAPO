/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.sql.*;
import javax.swing.JOptionPane;
import sapo.Main;
import sapo.conexao.Conectar;

/**
 *
 * @author Tiago
 */
public class SQLOperador {

    
    private Connection conn;
    private Statement stmt;
    private ResultSet r;

    public SQLOperador() throws SQLException {
        //Criando a conexao
        Conectar conexao = Main.getCon();
        //Setando a conexão
        conn = conexao.getConn();
        //Criando o Statement para conversação com o banco
        stmt = conn.createStatement();
    }

    public void delRegistro(String value) throws SQLException {
        String SQL = "DELETE FROM operador where codigo=" + value;
        stmt.executeUpdate(SQL);

    }

    public void excluir(String valor) throws SQLException {
        /* antes de excluir pergunta se deseja eliminar o cadastro */
        int opcao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que você deseja excluir esse cadastro?",
                "Exclusão Cadastrado",
                JOptionPane.YES_NO_OPTION);
        /* se for positivo inicia-se o processo de exclusão */
        if (opcao == JOptionPane.YES_OPTION) {
            //exclui os dados
            delRegistro(valor);
        }

    }

    public void insRegistros(String valor[]) {
        try {
            String SQL = "INSERT INTO operador " +
                    "VALUES(?,?,?)";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setString(1, valor[0]);
            ps.setString(2, valor[1]);
            ps.setString(3, valor[2]);
            /* executando a inserção */
            ps.executeUpdate();
            /* os campos serão limpos */
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //At registros altera o valores com rowId sendo o valor do código a ser alterado
    public void atRegistros(String valor[], String rowId) {
        int opcao = JOptionPane.showConfirmDialog(null,
                "Deseja salvar as alterações desse cadastro?",
                "Alteração de Cadastrado",
                JOptionPane.YES_NO_OPTION);
        /* se você escolher YES inicia-se o processo de atualização dos dados*/
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                String SQL = "UPDATE operador SET  " +
                        "login=?, senha=?" +
                        "WHERE codigo=?";
                /* Aqui você prepara a SQL para inserir os dados */
                PreparedStatement ps = conn.prepareStatement(SQL);
                /* que serão capturados aqui */
                ps.setString(1, valor[0]);
                ps.setString(2, valor[1]);
                /* pega o ID do registro para atualizar */
                ps.setString(3, rowId);
                /* executando a atualização */
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    
}
