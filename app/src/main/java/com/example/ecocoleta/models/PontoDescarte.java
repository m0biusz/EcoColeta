package com.example.ecocoleta.models;

public class PontoDescarte {
    private int id;
    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    private String tiposAceitos;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getTiposAceitos() { return tiposAceitos; }
    public void setTiposAceitos(String tiposAceitos) { this.tiposAceitos = tiposAceitos; }
}
