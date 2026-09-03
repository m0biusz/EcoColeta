package com.example.ecocoleta.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ocorrencia implements Serializable {
    private int id;
    private String protocolo;
    private String tipo;
    private String descricao;
    private String localizacao;
    private String data;
    private String status;
    private String imageUrl; // Placeholder for image
    private List<StatusHistory> historico;

    public Ocorrencia(String protocolo, String tipo, String data, String localizacao, String status) {
        this.protocolo = protocolo;
        this.tipo = tipo;
        this.data = data;
        this.localizacao = localizacao;
        this.status = status;
        this.historico = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public List<StatusHistory> getHistorico() { return historico; }
    public void setHistorico(List<StatusHistory> historico) { this.historico = historico; }
    
    public void addHistorico(StatusHistory entry) {
        if (this.historico == null) this.historico = new ArrayList<>();
        this.historico.add(0, entry); // Most recent first
    }
}
