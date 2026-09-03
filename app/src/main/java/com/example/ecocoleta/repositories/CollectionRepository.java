package com.example.ecocoleta.repositories;

import com.example.ecocoleta.api.EcoColetaApi;
import com.example.ecocoleta.api.RetrofitClient;
import com.example.ecocoleta.models.Coleta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CollectionRepository {
    private EcoColetaApi api;

    public CollectionRepository() {
        this.api = RetrofitClient.getInstance();
    }

    public void getColetas(Callback<List<Coleta>> callback) {
        Call<List<Coleta>> call = api.getColetas();
        call.enqueue(callback);
    }
}
