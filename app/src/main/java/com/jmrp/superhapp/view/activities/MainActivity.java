package com.jmrp.superhapp.view.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.jmrp.superhapp.IListSuperherosView;
import com.jmrp.superhapp.R;
import com.jmrp.superhapp.adapter.SuperherosAdapter;
import com.jmrp.superhapp.model.Superheros;
import com.jmrp.superhapp.retrofit.SuperHappClient;
import com.jmrp.superhapp.retrofit.SuperHappInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IListSuperherosView {

    private ProgressDialog pdChecking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSuperheros();
    }


    private void getSuperheros(){

        showProgress(getResources().getString(R.string.tv_checking_superheros));

        SuperHappClient.getSuperheros().create(SuperHappInterface.class).getSuperheros().enqueue(new Callback<Superheros>() {
            @Override
            public void onResponse(Call<Superheros> call, Response<Superheros> response) {

                dismissProgress();

                if(null!=response.body()) {

                    Log.i(getClass().getSimpleName(), "Superheros: " + new Gson().toJson(response));

                    RecyclerView rvSuperHeros = (RecyclerView) findViewById(R.id.rvSuperHeros);
                    rvSuperHeros.setHasFixedSize(true);

                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    rvSuperHeros.setLayoutManager(layoutManager);
                    SuperherosAdapter superherosAdapter = new SuperherosAdapter(MainActivity.this, response.body().superheros);
                    rvSuperHeros.setAdapter(superherosAdapter);
                }

            }

            @Override
            public void onFailure(Call<Superheros> call, Throwable t) {
                dismissProgress();
                showAlert(getResources().getString(R.string.error_something_wrong));
                call.cancel();
                t.printStackTrace();
            }
        });
    }


    @Override
    public void showAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getSuperheros();
                    }
                });
        alertDialog.show();
    }


    @Override
    public void showProgress(String message) {
        if(pdChecking==null) pdChecking = new ProgressDialog(MainActivity.this);
        pdChecking.setCancelable(false);
        pdChecking.setMessage(message);
        pdChecking.show();
    }


    @Override
    public void dismissProgress() {
        if(null!=pdChecking && pdChecking.isShowing()) pdChecking.dismiss();
    }
}
