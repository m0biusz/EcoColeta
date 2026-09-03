package com.example.ecocoleta.models;

public class Coleta {
    private String data;
    private String horario;
    private String tipo;
    private String regiao;
    private boolean proxima;

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }
    public boolean isProxima() { return proxima; }
    public void setProxima(boolean proxima) { this.proxima = proxima; }
}
