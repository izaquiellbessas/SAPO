/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sapo.Main;
import sapo.conexao.Conectar;
import sapo.model.AlunoModelo;

/**
 *
 * @author Tiago
 */
public class SQLAluno {

    private Connection conn;
    private Statement stmt;
    private ResultSet r;
    private ResultSet todosDados;
    private PreparedStatement pstm;

    public SQLAluno() throws SQLException {
        //Criando a conexao
        Conectar conexao = Main.getCon();
        //Setando a conexão
        conn = conexao.getConn();
        //Criando o Statement para conversação com o banco
        stmt = conn.createStatement();
        pstm = conn.prepareStatement("SELECT * FROM aluno");

    }

    public void delRegistro(int value) throws SQLException {
        String SQL = "DELETE FROM aluno where numMatricula =" + value;
        stmt.executeUpdate(SQL);
    }

    public void excluir(String valor) throws SQLException {
        //exclui os dados
        delRegistro(Integer.parseInt(valor));

    }

    public void insRegistros(String valor[]) {
        try {
            //Ordem dos valores: numMatricula, nome, dataNasc, classificacao, rua, numRua, bairro ,cep, telefone
            String SQL = "INSERT INTO aluno " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setString(1, valor[0]);
            ps.setString(2, valor[1].trim());
            ps.setDate(3, converteData(valor[2]));
            ps.setString(4, valor[3]);
            ps.setString(5, valor[4]);
            ps.setString(6, valor[5]);
            ps.setString(7, valor[6]);
            ps.setString(8, valor[7]);
            ps.setString(9, valor[8]);
            ps.setString(10, valor[9]);
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
                String SQL = "UPDATE aluno SET  " +
                        "nome=?, dataNasc=?, classificacao=?, rua=?, numRua=?, bairro=?, cidade=?, cep=?, telefone=? " +
                        "WHERE numMatricula=?";
                /* Aqui você prepara a SQL para inserir os dados */
                PreparedStatement ps = conn.prepareStatement(SQL);
                /* que serão capturados aqui */
                ps.setString(1, valor[1]);
                //Convertendo data para o formato do mysql com o procedimento converteData
                ps.setDate(2, converteData(valor[2]));
                ps.setString(3, valor[3]);
                ps.setString(4, valor[4]);
                ps.setString(5, valor[5]);
                ps.setString(6, valor[6]);
                ps.setString(7, valor[7]);
                ps.setString(8, valor[8]);
                ps.setString(9, valor[9]);
                /* pega o ID do registro para atualizar */
                ps.setString(10, rowId);
                /* executando a atualização */
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet todos() {
        try {
            todosDados = pstm.executeQuery();
            return this.todosDados;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private AlunoModelo dados(ResultSet rs) {
        try {
            if (rs.getRow() != 0) {
                AlunoModelo aluno = new AlunoModelo();
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCep(rs.getString("cep"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setClassifica(rs.getString("classificacao"));
                aluno.setDataNasc(rs.getDate("dataNasc"));
                aluno.setEndereco(rs.getString("rua"));
                aluno.setMatricula(rs.getInt("numMatricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNumeroRua(rs.getInt("numRua"));
                aluno.setTelefone(rs.getString("telefone"));
                return aluno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Date converteData(String data) {//Método para converter data para o formato do mysql
        Date d = null;
        try {
            if (data != null) {
                data = data.substring(6) + "-" +
                        data.substring(3, 5) + "-" +
                        data.substring(0, 2);
                d = Date.valueOf(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }

    public String dateToString(Date data) {
        //if (!data.equals("  /  /    ")) {
        String d = String.valueOf(data);
        String ret = null;
        if (data != null) {
            ret = d.substring(8, 10) + "/" +
                    d.substring(5, 7) + "/" +
                    d.substring(0, 4);
        }

        return ret;
    }

//funcoes para retornar posicoes específicas da tabela
    public AlunoModelo last() {
        AlunoModelo aluno = new AlunoModelo();
        try {
            todosDados = pstm.executeQuery();
            todosDados.last();
            aluno = dados(todosDados);
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AlunoModelo first() {
        AlunoModelo aluno = new AlunoModelo();
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

    public AlunoModelo localizar(
            int cod) {
        AlunoModelo aluno = new AlunoModelo();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM aluno WHERE numMatricula = ? ");
            pStm.setInt(1, cod);
            ResultSet rs = null;
            rs = pStm.executeQuery();
            rs.next();
            aluno =dados(rs);
            return aluno;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
