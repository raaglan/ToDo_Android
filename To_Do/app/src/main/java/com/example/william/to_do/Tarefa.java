package com.example.william.to_do;

import java.util.Date;
import java.util.Timer;

/**
 * Created by willi on 20/05/2016.
 */
public class Tarefa {

    private String titulo;
    private String descricao;
    private Date data;
    private String hora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
