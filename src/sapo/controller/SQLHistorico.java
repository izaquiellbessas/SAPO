/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sapo.Main;
import sapo.conexao.Conectar;
import sapo.model.HistoricoModelo;

/**
 *
 * @author christino
 */
public class SQLHistorico {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstm;
    private ResultSet todosDados;

    public SQLHistorico() throws SQLException {
        //Criando a conexao
        Conectar conexao = Main.getCon();
        //Setando a conexão
        conn = conexao.getConn();
        //Criando o Statement para conversação com o banco
        stmt = conn.createStatement();
        pstm = conn.prepareStatement("SELECT * FROM historico");

    }

    public void delRegistro(int codigo) throws SQLException {
        String SQL = "DELETE FROM historico where codHistorico =" + codigo;
        stmt.executeUpdate(SQL);
    }

    public void excluir(String valor) throws SQLException {
        //exclui os dados
        delRegistro(Integer.parseInt(valor));

    }

    public void insRegistros(HistoricoModelo modelo) {
        try {
            String SQL = "INSERT INTO historico ("+
                    "`Aluno_numMatricula`, `Orientador_codigo`, `Fases_codigoFase`, " +
                    "`Fases_Jogos_codigo`, `horaInicial`, `horaFinal`, `data`, " +
                    "`nivelDificuldade`, `qntdErros`) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setInt(1, modelo.getAlunoMatricula());
            ps.setInt(2, modelo.getCodigoOrientador());
            ps.setInt(3, modelo.getCodigoFase());
            ps.setInt(4, modelo.getFaseJogoCodigo());
            ps.setString(5, modelo.getHoraInicial());
            ps.setString(6, modelo.getHoraFinal());
            ps.setDate(7, modelo.getData());
            ps.setInt(8, modelo.getNivelDificuldade());
            ps.setInt(9, modelo.getQtdeErros());
            System.out.println(modelo.getCodigoFase());

            /* executando a inserção */
            ps.executeUpdate();
            /* os campos serão limpos */
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o Historico :\n"+ex);
        }

    }

    //At registros altera o valores com rowId sendo o valor do código a ser alterado
    public void atRegistros(HistoricoModelo modelo) {
        int opcao = JOptionPane.showConfirmDialog(null,
                "Deseja salvar as alterações desse cadastro?",
                "Alteração de Cadastrado",
                JOptionPane.YES_NO_OPTION);
        /* se você escolher YES inicia-se o processo de atualização dos dados*/
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                String SQL = "update historico  set("+
                    "`Aluno_numMatricula`=?, `Orientador_codigo`=?, `Fases_codigoFase`=?, " +
                    "`Fases_Jogos_codigo`=?, `horaInicial`=?, `horaFinal`=?, `data`=?, " +
                    "`nivelDificuldade`=?, `qntdErros`=?) " +
                    " where codHistorico=?";
            /* Aqui você prepara a SQL para inserir os dados */
            PreparedStatement ps = conn.prepareStatement(SQL);
            /* que serão capturados aqui */
            ps.setInt(1, modelo.getAlunoMatricula());
            ps.setInt(2, modelo.getCodigoOrientador());
            ps.setInt(3, modelo.getCodigoFase());
            ps.setInt(4, modelo.getFaseJogoCodigo());
            ps.setString(5, modelo.getHoraInicial());
            ps.setString(6, modelo.getHoraFinal());
            ps.setDate(7, modelo.getData());
            ps.setInt(8, modelo.getNivelDificuldade());
            ps.setInt(9, modelo.getQtdeErros());
            ps.setInt(9, modelo.getCodHistorico());
                /* executando a atualização */
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erro ao atualizar o Historico :\n"+ex);
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

    private HistoricoModelo dados(ResultSet rs) {
        try {
            if (rs.getRow() != 0) {
                HistoricoModelo modelo = new HistoricoModelo();
                modelo.setAlunoMatricula(rs.getInt("Aluno_numMatricula"));
                modelo.setCodHistorico(rs.getInt("codHistorico"));
                modelo.setCodigoFase(rs.getInt("Fases_codigoFase"));
                modelo.setCodigoOrientador(rs.getInt("Orientador_codigo"));
                modelo.setData(rs.getDate("data"));
                modelo.setFaseJogoCodigo(rs.getInt("Fases_Jogos_codigo"));
                modelo.setHoraFinal(rs.getString("horaFinal"));
                modelo.setHoraInicial(rs.getString("horaInicial"));
                modelo.setNivelDificuldade(rs.getInt("nivelDificuldade"));
                modelo.setQtdeErros(rs.getInt("qntdErros"));
                return modelo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/*
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
*/
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
    public HistoricoModelo last() {
        HistoricoModelo modelo = new HistoricoModelo();
        try {
            todosDados = pstm.executeQuery();
            todosDados.last();
            modelo = dados(todosDados);
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(SQLHistorico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HistoricoModelo first() {
        HistoricoModelo modelo = new HistoricoModelo();
        try {
            todosDados = pstm.executeQuery();
            todosDados.first();
            modelo = dados(todosDados);
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HistoricoModelo localizar(
            int cod) {
        HistoricoModelo modelo = new HistoricoModelo();
        try {
            PreparedStatement pStm = conn.prepareStatement("SELECT * FROM historico WHERE codHistorico = ? ");
            pStm.setInt(1, cod);
            ResultSet rs = null;
            rs = pStm.executeQuery();
            rs.next();
            modelo =dados(rs);
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(SQLAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
