package com.example.ecocoleta.api;

import com.example.ecocoleta.models.Aviso;
import com.example.ecocoleta.models.Coleta;
import com.example.ecocoleta.models.Ocorrencia;
import com.example.ecocoleta.models.PontoDescarte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EcoColetaApi {

    @GET("coletas")
    Call<List<Coleta>> getColetas();

    @GET("pontos-descarte")
    Call<List<PontoDescarte>> getPontosDescarte();

    @GET("avisos")
    Call<List<Aviso>> getAvisos();

    @GET("ocorrencias")
    Call<List<Ocorrencia>> getOcorrencias();

    @POST("ocorrencias")
    Call<Ocorrencia> postOcorrencia(@Body Ocorrencia ocorrencia);
}
