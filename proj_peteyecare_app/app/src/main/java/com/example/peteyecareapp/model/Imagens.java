package com.example.peteyecareapp.model;

import java.util.Date;

public class Imagens {

    private long id;
    private int imagens_idAnimal;
    private String url;
    private Date data_criacao;

    public Imagens(long id, int imagens_idAnimal, String url, Date data_criacao) {
        this.id = id;
        this.imagens_idAnimal = imagens_idAnimal;
        this.url = url;
        this.data_criacao = data_criacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImagens_idAnimal() {
        return imagens_idAnimal;
    }

    public void setImagens_idAnimal(int imagens_idAnimal) {
        this.imagens_idAnimal = imagens_idAnimal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
