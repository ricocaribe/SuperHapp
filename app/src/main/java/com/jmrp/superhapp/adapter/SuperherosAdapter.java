package com.jmrp.superhapp.adapter;

import android.app.Activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmrp.superhapp.R;
import com.jmrp.superhapp.model.Superheros;
import com.jmrp.superhapp.view.activities.SuperheroDetailActivity;
import com.squareup.picasso.Picasso;


import java.util.List;

public class SuperherosAdapter extends RecyclerView.Adapter<SuperherosAdapter.ViewHolder> {

    private List<Superheros.Superhero> superheros;
    private Activity activity;

    public SuperherosAdapter(Activity act, List<Superheros.Superhero> superherosList) {
        superheros = superherosList;
        activity = act;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView superheroName;
        ImageView superheroImage;

        ViewHolder(View v) {
            super(v);
            superheroName = (TextView) v.findViewById(R.id.superheroName);
            superheroImage = (ImageView) v.findViewById(R.id.superheroImage);
        }
    }


    @Override
    public SuperherosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_superhero, parent, false);
        return new SuperherosAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SuperherosAdapter.ViewHolder holder, final int position) {

        Picasso.with(holder.itemView.getContext())
                .load(superheros.get(position).photo)
                .fit().centerCrop()
                .into(holder.superheroImage);

        holder.superheroName.setText(superheros.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SuperheroDetailActivity.class);
                intent.putExtra("superhero", superheros.get(position));
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return superheros.size();
    }
}