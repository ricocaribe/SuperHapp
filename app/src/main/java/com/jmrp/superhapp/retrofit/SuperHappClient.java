package com.jmrp.superhapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuperHappClient {

    public static Retrofit getSuperheros() {

        return new Retrofit.Builder()
                .baseUrl(SuperHappInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
