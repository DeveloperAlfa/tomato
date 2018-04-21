package com.example.developeralfa.tomatoes.TV.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.TV.Retrofit.Season;
import com.example.developeralfa.tomatoes.TV.Retrofit.TVShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Season> seasons;
    OnViewClicked viewClicked;

    public RecyclerAdapter(Context context, ArrayList<Season> seasons, OnViewClicked viewClicked) {
        this.context = context;
        this.seasons = seasons;
        this.viewClicked = viewClicked;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.actor,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {


        holder.movieName.setText(seasons.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewClicked.onClicked(seasons.get(position).getSeason_number());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+seasons.get(position).getPoster_path()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView poster;

        TextView movieName;
        public CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            poster = view.findViewById(R.id.poster);

            movieName = view.findViewById(R.id.personName);
        }
    }
    interface  OnViewClicked
    {
        void onClicked(int id);
    }
}
