package com.jmrp.superhapp.presenter;


import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.jmrp.superhapp.interactor.MainInteractor;
import com.jmrp.superhapp.model.Superheros;
import com.jmrp.superhapp.retrofit.SuperHappClient;
import com.jmrp.superhapp.retrofit.SuperHappInterface;
import com.jmrp.superhapp.view.activities.SuperheroDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainInteractor.MainPresenter {

    private MainInteractor.MainView mainView;

    // El presentador recibe su vista para devolverle cosas.
    @Override
    public void setVista(MainInteractor.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getSuperheros(){

        mainView.showProgressDialog();

        SuperHappClient.getSuperheros().create(SuperHappInterface.class).getSuperheros().enqueue(new Callback<Superheros>() {
            @Override
            public void onResponse(Call<Superheros> call, Response<Superheros> response) {

                mainView.dismissProgressDialog();

                if(null!=response.body()) {
                    Log.i(getClass().getSimpleName(), "Superheros: " + new Gson().toJson(response));
                    mainView.setSuperherosAdapter(response.body().superheros);
                }

            }

            @Override
            public void onFailure(Call<Superheros> call, Throwable t) {
                mainView.dismissProgressDialog();
                mainView.showAlert();
                call.cancel();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void goDetailActivity(Superheros.Superhero superhero) {
        mainView.goSuperheroDetail(superhero);
    }
}
