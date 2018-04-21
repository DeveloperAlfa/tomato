package com.example.developeralfa.tomatoes.Movies.Genres;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.Genre;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.R;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 19-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomHolder> {
    Context context;
    ArrayList<Genre> genres=new ArrayList<>();
    OnGenreClicked onGenreClicked;


    public RecyclerAdapter(Context context, ArrayList<Genre> genres, OnGenreClicked onGenreClicked) {
        this.context = context;
        this.genres = genres;
        this.onGenreClicked = onGenreClicked;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.genre,parent,false);
        com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter.CustomHolder customHolder = new com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter.CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, final int position) {
        holder.genreName.setText(genres.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGenreClicked.onGenreClickedListener(genres.get(position).getId(),genres.get(position).getName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder
    {
        View view;

        TextView genreName;
        public CustomHolder(View itemView) {
            super(itemView);
            view = itemView;

            genreName = view.findViewById(R.id.GenreName);
        }
    }
    public interface OnGenreClicked
    {
        void onGenreClickedListener(int id,String genre);
    }
}
