package com.example.peteyecareapp.model;

import java.util.Date;

public class Usuario {
    private long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private int celular;
    private int usu_idEndereco;
    private int usu_idAnimal;
    private int usu_idWifi;
    private Date data_criacao;

    public Usuario(long id, String cpf, String nome, String sobrenome, String email, String senha, int celular, int usu_idEndereco, int usu_idAnimal, int usu_idWifi, Date data_criacao) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.usu_idEndereco = usu_idEndereco;
        this.usu_idAnimal = usu_idAnimal;
        this.usu_idWifi = usu_idWifi;
        this.data_criacao = data_criacao;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getUsu_idEndereco() {
        return usu_idEndereco;
    }

    public void setUsu_idEndereco(int usu_idEndereco) {
        this.usu_idEndereco = usu_idEndereco;
    }

    public int getUsu_idAnimal() {
        return usu_idAnimal;
    }

    public void setUsu_idAnimal(int usu_idAnimal) {
        this.usu_idAnimal = usu_idAnimal;
    }

    public int getUsu_idWifi() {
        return usu_idWifi;
    }

    public void setUsu_idWifi(int usu_idWifi) {
        this.usu_idWifi = usu_idWifi;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
