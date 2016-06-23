/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sapo.model;

/**
 *
 * @author wsouza
 */
public class OrientadorModelo {
    private int codigo;
    private String nome;
    private String endereco;
    private int numRua;
    private String bairro;
    private String cidade;
    private String cep;
    private String telefone;
    private String profissao;
    private String login;
    private String senha;

    public OrientadorModelo(int codigo, String nome, String endereco, int numRua, String bairro, String cidade, String cep, String telefone, String profissao, String login, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.numRua = numRua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.telefone = telefone;
        this.profissao = profissao;
        this.login = login;
        this.senha = senha;
    }
    

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }



    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumRua() {
        return numRua;
    }

    public void setNumRua(int numRua) {
        this.numRua = numRua;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public OrientadorModelo() {
    }
}
