package com.jmrp.superhapp.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmrp.superhapp.R;


public class InfoSuperheroGroups extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_superhero_groups, container, false);

        TextView txtInfoEventName = (TextView) rootView.findViewById(R.id.superheroDetailGroups);
        txtInfoEventName.setText(getArguments().get("superheroGroups")!=null?getArguments().get("superheroGroups").toString():"");

        return rootView;
    }

    public static InfoSuperheroGroups newInstance(String groups) {

        InfoSuperheroGroups f = new InfoSuperheroGroups();
        Bundle b = new Bundle();
        b.putString("superheroGroups", groups);
        f.setArguments(b);
        return f;
    }
}