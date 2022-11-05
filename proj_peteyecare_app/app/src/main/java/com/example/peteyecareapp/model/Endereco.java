package com.example.peteyecareapp.model;

import java.util.Date;

public class Endereco {
    private long id;
    private int endereco_idUsuario;
    private int cep;
    private int numeroResidencia;
    private String complemento;
    private Date data_criacao;

    public Endereco(long id, int endereco_idUsuario, int cep, int numeroResidencia, String complemento, Date data_criacao) {
        this.id = id;
        this.endereco_idUsuario = endereco_idUsuario;
        this.cep = cep;
        this.numeroResidencia = numeroResidencia;
        this.complemento = complemento;
        this.data_criacao = data_criacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEndereco_idUsuario() {
        return endereco_idUsuario;
    }

    public void setEndereco_idUsuario(int endereco_idUsuario) {
        this.endereco_idUsuario = endereco_idUsuario;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
