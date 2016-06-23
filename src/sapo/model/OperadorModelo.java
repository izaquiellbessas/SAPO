/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.model;

/**
 *
 * @author wsouza
 */
public class OperadorModelo {
    private int codigo;
    private String login;
    private String senha;

    public OperadorModelo() {
    }

    public OperadorModelo(int codigo, String login, String senha) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
