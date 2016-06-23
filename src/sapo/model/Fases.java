/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.model;

/**
 *
 * @author christino
 */
public class Fases {

    private int jogos_codigo;
    private int codigoFase;
    private String nome;

    public Fases(int jogos_codigo, int codigoFase, String nome, int nivelDificuldade, int tempoLimite) {
        this.jogos_codigo = jogos_codigo;
        this.codigoFase = codigoFase;
        this.nome = nome;
        this.nivelDificuldade = nivelDificuldade;
        this.tempoLimite = tempoLimite;
    }

    public Fases() {
    }

    public int getCodigoFase() {
        return codigoFase;
    }

    public void setCodigoFase(int codigoFase) {
        this.codigoFase = codigoFase;
    }

    public int getJogos_codigo() {
        return jogos_codigo;
    }

    public void setJogos_codigo(int jogos_codigo) {
        this.jogos_codigo = jogos_codigo;
    }

    public int getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(int nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
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
    private int nivelDificuldade;
    private int tempoLimite;
}
