package com.example.finalprojecthp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PotterDBService {
    private static final String BASE_URL = "https://potterdb.com/api/v1/";

    private Retrofit retrofit;

    public PotterDBService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<PotterDBResponse> getPotterDBResponse() {
        return retrofit.create(PotterDBService.class).getPotterDBResponse();
    }
}
