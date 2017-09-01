package com.jmrp.superhapp.retrofit;

import com.jmrp.superhapp.model.Superheros;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperHappInterface {
    String BASE_URL = "https://api.myjson.com/";
    @GET("bins/bvyob")
    Call<Superheros> getSuperheros();

}
