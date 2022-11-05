package com.example.peteyecareapp.model;

import java.util.Date;

public class Animal {
    private long id;
    private String tipo;
    private String nomeAnimal;
    private String sobrenomeAnimal;
    private int animal_idEndereco;
    private int animal_idUsuario;
    private Date data_criacao;

    public Animal(long id, String tipo, String nomeAnimal, String sobrenomeAnimal, int animal_idEndereco, int animal_idUsuario, Date data_criacao) {
        this.id = id;
        this.tipo = tipo;
        this.nomeAnimal = nomeAnimal;
        this.sobrenomeAnimal = sobrenomeAnimal;
        this.animal_idEndereco = animal_idEndereco;
        this.animal_idUsuario = animal_idUsuario;
        this.data_criacao = data_criacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getSobrenomeAnimal() {
        return sobrenomeAnimal;
    }

    public void setSobrenomeAnimal(String sobrenomeAnimal) {
        this.sobrenomeAnimal = sobrenomeAnimal;
    }

    public int getAnimal_idEndereco() {
        return animal_idEndereco;
    }

    public void setAnimal_idEndereco(int animal_idEndereco) {
        this.animal_idEndereco = animal_idEndereco;
    }

    public int getAnimal_idUsuario() {
        return animal_idUsuario;
    }

    public void setAnimal_idUsuario(int animal_idUsuario) {
        this.animal_idUsuario = animal_idUsuario;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
