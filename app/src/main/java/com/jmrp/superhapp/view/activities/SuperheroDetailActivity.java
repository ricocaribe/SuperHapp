package com.jmrp.superhapp.view.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmrp.superhapp.R;
import com.jmrp.superhapp.view.adapter.SuperherosDetailInfoAdapter;
import com.jmrp.superhapp.model.Superheros;
import com.jmrp.superhapp.utils.ZoomOutPageTransformer;
import com.squareup.picasso.Picasso;

public class SuperheroDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_detail);

        Superheros.Superhero superhero = (Superheros.Superhero) getIntent().getSerializableExtra("superhero");

        ImageView superheroDetailCircImage = (ImageView) findViewById(R.id.superheroDetailCircImage);

        Picasso.with(getApplicationContext())
                .load(superhero.photo)
                .fit().centerCrop()
                .into(superheroDetailCircImage);

        TextView superheroDetailName = (TextView) findViewById(R.id.superheroDetailName);
        superheroDetailName.setText(superhero.name);

        TextView superheroDetailHeight = (TextView) findViewById(R.id.superheroDetailHeight);
        superheroDetailHeight.setText(superhero.height);

        ViewPager viewPager = (ViewPager) findViewById(R.id.superheroDetailInfoVp);
        PagerAdapter mPagerAdapter = new SuperherosDetailInfoAdapter(getSupportFragmentManager(), superhero);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }
}
