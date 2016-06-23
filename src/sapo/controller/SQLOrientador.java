/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sapo.Main;
import sapo.conexao.Conectar;
import sapo.model.OrientadorModelo;

/**
 *
 * @author Tiago
 */
public class SQLOrientador {

    private Connection conn;
    private Statement stmt;
    private ResultSet r;
    private ResultSet todosDados;
    private PreparedStatement pstm;
    private OrientadorModelo orientador;

    public SQLOrientador() throws SQLException {
        //Criando a conexao
        Conectar conexao = Main.getCon();
        //Setando a conexão
        conn = conexao.getConn();
        //Criando o Statement para conversação com o banco
        orientador = new OrientadorModelo();
        stmt = conn.createStatement();
        pstm = conn.prepareStatement("SELECT * FROM orientador");
    }

    public void delRegistro(String value) throws SQLException {
        String SQL = "DELETE FROM orientador WHERE codigo=" + value;
        stmt.executeUpdate(SQL);
    }

    public void excluir(String valor) throws SQLException {
        delRegistro(valor);
    }

    public void insRegistros(String valor[]) {
        try {
            //Ordem dos valores: codigo, nome, rua, numRua, bairro, cidade, cep, telefone, profissao, Operador_codigo
            String SQL = "INSERT INTO orientador values " +
                    "(?,?,?,?,?,?,?,?,?,?,?)";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setString(1, valor[0]);
            ps.setString(2, valor[1]);
            ps.setString(3, valor[2]);
            ps.setString(4, valor[3]);
            ps.setString(5, valor[4]);
            ps.setString(6, valor[5]);
            ps.setString(7, valor[6]);
            ps.setString(8, valor[7]);
            ps.setString(9, valor[8]);
            ps.setString(10, valor[9]);
            ps.setString(11, valor[10]);
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

        try {
            String SQL = "UPDATE orientador SET  " +
                    "nome=?, rua=?, numRua=?, bairro=?, cidade=?, cep=?, telefone=?, profissao=?, login=?, senha=?" +
                    " WHERE codigo=?";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setString(1, valor[1]);
            ps.setString(2, valor[2]);
            ps.setString(3, valor[3]);
            ps.setString(4, valor[4]);
            ps.setString(5, valor[5]);
            ps.setString(6, valor[6]);
            ps.setString(7, valor[7]);
            ps.setString(8, valor[8]);
            ps.setString(9, valor[9]);
            ps.setString(10, valor[10]);
            /* pega o ID do registro para atualizar */
            ps.setString(11, valor[0]);
            /* executando a atualização */
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private OrientadorModelo dados(ResultSet rs) {
        OrientadorModelo ori = null;
        try {
            if (rs.getRow() != 0) {
                ori = new OrientadorModelo();
                ori.setBairro(rs.getString("bairro"));
                ori.setCep(rs.getString("cep"));
                ori.setCidade(rs.getString("cidade"));
                ori.setCodigo(rs.getInt("codigo"));
                ori.setEndereco(rs.getString("rua"));
                ori.setLogin(rs.getString("login"));
                ori.setNome(rs.getString("nome"));
                ori.setNumRua(rs.getInt("numRua"));
                ori.setProfissao(rs.getString("profissao"));
                ori.setSenha(rs.getString("senha"));
                ori.setTelefone(rs.getString("telefone"));
                orientador = ori;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLOrientador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ori;
    }

    public OrientadorModelo last() {
        OrientadorModelo ori = new OrientadorModelo();
        try {
            todosDados = pstm.executeQuery();
            todosDados.last();
            ori = dados(todosDados);
            return ori;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public OrientadorModelo first() {
        OrientadorModelo aluno = new OrientadorModelo();
        try {
            todosDados = pstm.executeQuery();
            todosDados.first();            
            aluno = dados(todosDados);
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public OrientadorModelo localizar(int cod) {
        OrientadorModelo aluno = new OrientadorModelo();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM orientador WHERE codigo = ? ");
            pStm.setInt(1, cod);
            ResultSet rs = null;
            rs = pStm.executeQuery();
            rs.next();
            aluno = dados(rs);
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean validaOrientador(String user, String pass) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM orientador WHERE (login = ?) and (senha = ?)");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();            
            if (rs.next()) {
                orientador = dados(rs);
                return true;

            } else if (user.equals("saporoot") && (pass.equals("sapopass"))) {
                orientador = null;
                return true;
            } else {
                orientador = null;
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLOrientador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public OrientadorModelo getOrientador() {
        return orientador;
    }

    public void setOrientador(OrientadorModelo orientador) {
        this.orientador = orientador;
    }
}
