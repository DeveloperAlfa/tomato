package com.example.developeralfa.tomatoes.TV;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Movies.SingleMovieActivity;
import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.TV.Activities.SingleShowActivity;
import com.example.developeralfa.tomatoes.TV.Retrofit.TVShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    Context context;
    ArrayList<TVShow> tvShows;

    public RecyclerAdapter(Context context, ArrayList<TVShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.stars.setText(tvShows.get(position).getVote_average());
        holder.movieName.setText(tvShows.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleShowActivity.class);
                intent.putExtra("id",tvShows.get(position).getId());
                context.startActivity(intent);
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tvShows.get(position).getPoster_path()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView poster;
        TextView stars;
        TextView movieName;
        public CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            poster = view.findViewById(R.id.poster);
            stars = view.findViewById(R.id.stars);
            movieName = view.findViewById(R.id.movieName);
        }
    }
}
