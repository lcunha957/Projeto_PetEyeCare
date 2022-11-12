package com.example.peteyecareapp.dbHelper;

public class Cleaner {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private String usu_idEndereco;
    private String usu_idAnimal;
    private String usu_idWifi;
    private String data_criacao;

    public Cleaner(String nome, String sobrenome, String cpf, String email, String senha, String usu_idEndereco, String usu_idAnimal, String usu_idWifi, String data_criacao, String s, String toString, String string, String s1){
        nome = nome;
        sobrenome = sobrenome;
        cpf = cpf;
        email = email;
        senha = senha;
        usu_idEndereco = usu_idEndereco;
        usu_idAnimal = usu_idAnimal;
        usu_idWifi = usu_idWifi;
        data_criacao = data_criacao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getUsu_idEndereco() {
        return usu_idEndereco;
    }

    public void setUsu_idEndereco(String usu_idEndereco) {
        this.usu_idEndereco = usu_idEndereco;
    }

    public String getUsu_idAnimal() {
        return usu_idAnimal;
    }

    public void setUsu_idAnimal(String usu_idAnimal) {
        this.usu_idAnimal = usu_idAnimal;
    }

    public String getUsu_idWifi() {
        return usu_idWifi;
    }

    public void setUsu_idWifi(String usu_idWifi) {
        this.usu_idWifi = usu_idWifi;
    }

    public String getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(String data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Cleaner(){

    }
}
