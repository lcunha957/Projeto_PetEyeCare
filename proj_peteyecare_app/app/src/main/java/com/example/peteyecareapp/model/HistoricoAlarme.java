package com.example.peteyecareapp.model;

import java.util.Date;

public class HistoricoAlarme {

    private long id;
    private int histam_idUsuario;
    private String mensagem;
    private Date data_criacao;

    public HistoricoAlarme(long id, int histam_idUsuario, String mensagem, Date data_criacao) {
        this.id = id;
        this.histam_idUsuario = histam_idUsuario;
        this.mensagem = mensagem;
        this.data_criacao = data_criacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHistam_idUsuario() {
        return histam_idUsuario;
    }

    public void setHistam_idUsuario(int histam_idUsuario) {
        this.histam_idUsuario = histam_idUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
