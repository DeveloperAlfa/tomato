package com.example.developeralfa.tomatoes.Movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 17-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomHolder> {
    Context context;
    ArrayList<Movie> movies=new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout,parent,false);
        CustomHolder customHolder = new CustomHolder(view);
        return customHolder;

    }

    @Override
    public void onBindViewHolder(CustomHolder holder, final int position) {
        holder.stars.setText(movies.get(position).getVote_average());
        holder.movieName.setText(movies.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleMovieActivity.class);
                intent.putExtra("id",movies.get(position).getId());
                context.startActivity(intent);
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movies.get(position).getPoster_path()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView poster;
        TextView stars;
        TextView movieName;
        public CustomHolder(View itemView) {
            super(itemView);
            view = itemView;
            poster = view.findViewById(R.id.poster);
            stars = view.findViewById(R.id.stars);
            movieName = view.findViewById(R.id.movieName);
        }
    }
}
