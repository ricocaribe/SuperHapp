package com.jmrp.superhapp.view.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jmrp.superhapp.adapter.SuperherosAdapter;
import com.jmrp.superhapp.interactor.MainInteractor;
import com.jmrp.superhapp.R;
import com.jmrp.superhapp.dagger.MainModule;
import com.jmrp.superhapp.model.Superheros;

import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class MainActivity extends AppCompatActivity implements MainInteractor.MainView {

    @Inject
    MainInteractor.MainPresenter mainPresenter;
    private ProgressDialog pdChecking;
    private RecyclerView rvSuperHeros;
    private SuperherosAdapter superherosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new MainModule());
        objectGraph.inject(this);

        // Le dice al presenter cuál es su vista
        mainPresenter.setVista(this);

        configView();

        mainPresenter.getSuperheros();
    }


    private void configView(){
        pdChecking = new ProgressDialog(MainActivity.this);
        rvSuperHeros = (RecyclerView) findViewById(R.id.rvSuperHeros);
        rvSuperHeros.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rvSuperHeros.setLayoutManager(layoutManager);

        superherosAdapter = new SuperherosAdapter(mainPresenter);
    }


    @Override
    public void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage(getResources().getString(R.string.error_something_wrong));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mainPresenter.getSuperheros();
                    }
                });
        alertDialog.show();
    }


    @Override
    public void showProgressDialog() {
        pdChecking.setCancelable(false);
        pdChecking.setMessage(getResources().getString(R.string.tv_checking_superheros));
        pdChecking.show();
    }


    @Override
    public void dismissProgressDialog() {
        if(null!=pdChecking && pdChecking.isShowing()) pdChecking.dismiss();
    }

    @Override
    public void setSuperherosAdapter(List<Superheros.Superhero> superheros) {
        superherosAdapter.setSuperheros(superheros);
        rvSuperHeros.setAdapter(superherosAdapter);
    }

    @Override
    public void goSuperheroDetail(Superheros.Superhero superhero) {
        Intent intent = new Intent(MainActivity.this, SuperheroDetailActivity.class);
        intent.putExtra("superhero", superhero);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
