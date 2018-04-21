package com.example.developeralfa.tomatoes.Actors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Actors.Retrofit.Person;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.Movies.SingleMovieActivity;
import com.example.developeralfa.tomatoes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomHolder> {
    Context context;
    ArrayList<Person> people=new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<Person> movies) {
        this.context = context;
        this.people = movies;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.actor,parent,false);

        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, final int position) {

        holder.personName.setText(people.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleActorActivity.class);
                intent.putExtra("id",people.get(position).getId());
                context.startActivity(intent);
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+people.get(position).getProfile_path()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView poster;
        TextView personName;
        public CustomHolder(View itemView) {
            super(itemView);
            view = itemView;
            poster = view.findViewById(R.id.poster);

            personName = view.findViewById(R.id.personName);
        }
    }
}
