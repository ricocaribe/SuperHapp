package com.jmrp.superhapp.interactor;

import com.jmrp.superhapp.model.Superheros;

import java.util.List;

public interface MainInteractor {

    interface MainView {
        void showAlert();
        void showProgressDialog();
        void dismissProgressDialog();
        void setSuperherosAdapter(List<Superheros.Superhero> superheros);
        void goSuperheroDetail(Superheros.Superhero superhero);
    }

    interface MainPresenter {
        void setVista(MainView vista);
        void getSuperheros();
        void goDetailActivity(Superheros.Superhero superhero);
    }
}
