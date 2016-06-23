/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.conexao;

/**
 *
 * @author home
 */
public class Usuario {

    private String senha;
    private String usuario;
    private String url;

    public Usuario() {
    }

    public Usuario(String senha, String usuario, String url) {
        this.senha = senha;
        this.usuario = usuario;
        this.url = url;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
