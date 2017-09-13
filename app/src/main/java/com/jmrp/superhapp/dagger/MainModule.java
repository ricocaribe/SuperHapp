package com.jmrp.superhapp.dagger;

import com.jmrp.superhapp.interactor.MainInteractor;
import com.jmrp.superhapp.presenter.MainActivityPresenter;
import com.jmrp.superhapp.view.activities.MainActivity;

import dagger.Module;
import dagger.Provides;


@Module(injects = {MainActivity.class})
public class MainModule {

    @Provides
    public MainInteractor.MainPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter();
    }
}
