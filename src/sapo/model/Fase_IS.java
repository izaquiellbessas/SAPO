/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.model;

/**
 *
 * @author christino
 */
public class Fase_IS {

    private int idFases_IS;

    public Fase_IS(int idFases_IS, String URL_imagem, String URL_sons, String nome_is) {
        this.idFases_IS = idFases_IS;
        this.URL_imagem = URL_imagem;
        this.URL_sons = URL_sons;
        this.nome_is = nome_is;
    }

    public Fase_IS() {
    }
    private String URL_imagem;
    private String URL_sons;
    private String nome_is;

    public String getURL_imagem() {
        return URL_imagem;
    }

    public void setURL_imagem(String URL_imagem) {
        this.URL_imagem = URL_imagem;
    }

    public String getURL_sons() {
        return URL_sons;
    }

    public void setURL_sons(String URL_sons) {
        this.URL_sons = URL_sons;
    }

    public int getIdFases_IS() {
        return idFases_IS;
    }

    public void setIdFases_IS(int idFases_IS) {
        this.idFases_IS = idFases_IS;
    }

    public String getNome_is() {
        return nome_is;
    }

    public void setNome_is(String nome_is) {
        this.nome_is = nome_is;
    }
}
