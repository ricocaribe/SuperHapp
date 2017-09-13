package com.jmrp.superhapp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jmrp.superhapp.model.Superheros;
import com.jmrp.superhapp.view.fragments.InfoSuperheroGroups;
import com.jmrp.superhapp.view.fragments.InfoSuperheroPowerAbilities;


public class SuperherosDetailInfoAdapter extends FragmentStatePagerAdapter {

    private Superheros.Superhero superhero;

    public SuperherosDetailInfoAdapter(FragmentManager fm, Superheros.Superhero sh) {
        super(fm);
        this.superhero = sh;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return InfoSuperheroPowerAbilities.newInstance(superhero.power, superhero.abilities);
            case 1: return InfoSuperheroGroups.newInstance(superhero.groups);
            default:break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
