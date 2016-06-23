/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.controller;

import java.sql.*;
import sapo.Main;
import sapo.conexao.Conectar;

/**
 *
 * @author christino
 */
public class SQLFases {
    private Connection conn;
    private Statement stmt;
    private ResultSet todosDados;
    private PreparedStatement pstm;

     public SQLFases() throws SQLException {
        //Criando a conexao
        Conectar conexao = Main.getCon();
        //Setando a conexão
        conn = conexao.getConn();
        //Criando o Statement para conversação com o banco
        stmt = conn.createStatement();
        pstm = conn.prepareStatement("SELECT * FROM Fases");

    }

     public ResultSet retornaFases(int codigoFase,int codigoJogo) throws SQLException{
         return  conn.prepareStatement("SELECT * FROM Fases where Jogos_codigo ="
                 +codigoJogo+ " and codigoFase= "+codigoFase).executeQuery();
     }

     public ResultSet retornaFases_IS(int codigoFase,int CodigoJogo) throws SQLException{
         return  conn.prepareStatement("select *  "+
                            " from fases_is_has_fases F  "+
                            " inner join fases_is img on img.idFases_is =Fases_is_idFases_is "+
                            " where fases_codigoFase="+ codigoFase+
                            " and Fases_Jogos_codigo="+CodigoJogo).executeQuery();
     }


}