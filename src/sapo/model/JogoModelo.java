/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.model;

/**
 *
 * @author wsouza
 */
public class JogoModelo {
    private int codigo;
    private String nome;

    public JogoModelo() {
    }

    public JogoModelo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
