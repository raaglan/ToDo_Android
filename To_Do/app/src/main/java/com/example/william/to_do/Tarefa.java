package com.example.william.to_do;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Timer;

/**
 * Created by willi on 20/05/2016.
 */
public class Tarefa implements Parcelable {

    private String key;
    private String titulo;
    private String descricao;
    private String data;
    private String hora;

    public Tarefa(){

    }

    protected Tarefa(Parcel in) {
        titulo = in.readString();
        descricao = in.readString();
        data = in.readString();
        hora = in.readString();
    }

    public static final Creator<Tarefa> CREATOR = new Creator<Tarefa>() {
        @Override
        public Tarefa createFromParcel(Parcel in) {
            return new Tarefa(in);
        }

        @Override
        public Tarefa[] newArray(int size) {
            return new Tarefa[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(descricao);
        dest.writeString(data);
        dest.writeString(hora);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
