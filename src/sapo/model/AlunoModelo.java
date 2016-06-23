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
public class AlunoModelo {
    private int matricula;
    private String nome;
    private Date dataNasc;
    private String classifica;
    private String endereco;
    private int numeroRua;
    private String bairro;
    private String cep;
    private String cidade;
    private String telefone;

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

    public String getClassifica() {
        return classifica;
    }

    public void setClassifica(String classifica) {
        this.classifica = classifica;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(int numeroRua) {
        this.numeroRua = numeroRua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public AlunoModelo(int matricula, String nome, Date dataNasc, String classifica, String endereco, int numeroRua, String bairro, String cep, String cidade, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.classifica = classifica;
        this.endereco = endereco;
        this.numeroRua = numeroRua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public AlunoModelo() {
    }

}
