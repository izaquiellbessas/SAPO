/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.model;

import java.sql.Date;

/**
 *
 * @author wsouza
 */
public class HistoricoModelo {
    private int codHistorico;
    private int alunoMatricula;
    private int codigoOrientador;
    private int codigoFase;
    private int faseJogoCodigo;
    private String HoraInicial;
    private String HoraFinal;
    private Date data;
    private int nivelDificuldade;
    private int qtdeErros;

    public HistoricoModelo() {
    }

    public HistoricoModelo(int codHistorico, int alunoMatricula, int codigoOrientador, int codigoFase, int faseJogoCodigo, String HoraInicial, String HoraFinal, Date data, int nivelDificuldade, int qtdeErros) {
        this.codHistorico = codHistorico;
        this.alunoMatricula = alunoMatricula;
        this.codigoOrientador = codigoOrientador;
        this.codigoFase = codigoFase;
        this.faseJogoCodigo = faseJogoCodigo;
        this.HoraInicial = HoraInicial;
        this.HoraFinal = HoraFinal;
        this.data = data;
        this.nivelDificuldade = nivelDificuldade;
        this.qtdeErros = qtdeErros;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String HoraFinal) {
        this.HoraFinal = HoraFinal;
    }

    public String getHoraInicial() {
        return HoraInicial;
    }

    public void setHoraInicial(String HoraInicial) {
        this.HoraInicial = HoraInicial;
    }

    public int getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(int alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public int getCodHistorico() {
        return codHistorico;
    }

    public void setCodHistorico(int codHistorico) {
        this.codHistorico = codHistorico;
    }

    public int getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(int codigoFase) {
        this.codigoFase = codigoFase;
    }

    public int getCodigoOrientador() {
        return codigoOrientador;
    }

    public void setCodigoOrientador(int codigoOrientador) {
        this.codigoOrientador = codigoOrientador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getFaseJogoCodigo() {
        return faseJogoCodigo;
    }

    public void setFaseJogoCodigo(int faseJogoCodigo) {
        this.faseJogoCodigo = faseJogoCodigo;
    }

    public int getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(int nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public int getQtdeErros() {
        return qtdeErros;
    }

    public void setQtdeErros(int qtdeErros) {
        this.qtdeErros = qtdeErros;
    }
    
}
