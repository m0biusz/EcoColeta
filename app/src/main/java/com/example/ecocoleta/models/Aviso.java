package com.example.ecocoleta.models;

public class Aviso {
    private int id;
    private String titulo;
    private String mensagem;
    private String data;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
