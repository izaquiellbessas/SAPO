/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.model;

/**
 *
 * @author wsouza
 */
public class FasesModelo {
    private int codigoJogo;
    private int codigoFase;
    private String nome;
    private int nivelDifi;
    private int tempoLimite; //tempo total gasto para excutar a tarefa, em minutos

    public int getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(int codigoFase) {
        this.codigoFase = codigoFase;
    }

    public int getCodigoJogo() {
        return codigoJogo;
    }

    public void setCodigoJogo(int codigoJogo) {
        this.codigoJogo = codigoJogo;
    }

    public int getNivelDifi() {
        return nivelDifi;
    }

    public void setNivelDifi(int nivelDifi) {
        this.nivelDifi = nivelDifi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoLimite() {
        return tempoLimite;
    }

    public void setTempoLimite(int tempoLimite) {
        this.tempoLimite = tempoLimite;
    }
    public FasesModelo(int codigoJogo, int codigoFase, String nome, int nivelDifi, int tempoLimite) {
        this.codigoJogo = codigoJogo;
        this.codigoFase = codigoFase;
        this.nome = nome;
        this.nivelDifi = nivelDifi;
        this.tempoLimite = tempoLimite;
    }
    public FasesModelo() {
    }

}
