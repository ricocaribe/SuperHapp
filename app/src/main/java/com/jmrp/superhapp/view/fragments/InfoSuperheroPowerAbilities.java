package com.jmrp.superhapp.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmrp.superhapp.R;


public class InfoSuperheroPowerAbilities extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_superhero_power_abilities, container, false);

        TextView superheroDetailPower = (TextView) rootView.findViewById(R.id.superheroDetailPower);
        superheroDetailPower.setText(getArguments().get("superheroPower")!=null?getArguments().get("superheroPower").toString():"");

        TextView superheroDetailAbilities = (TextView) rootView.findViewById(R.id.superheroDetailAbilities);
        superheroDetailAbilities.setText(getArguments().get("superheroAbilities")!=null?getArguments().get("superheroAbilities").toString():"");

        return rootView;
    }

    public static InfoSuperheroPowerAbilities newInstance(String power, String abilities) {
        InfoSuperheroPowerAbilities f = new InfoSuperheroPowerAbilities();
        Bundle b = new Bundle();
        b.putString("superheroPower", power);
        b.putString("superheroAbilities", abilities);
        f.setArguments(b);
        return f;
    }
}