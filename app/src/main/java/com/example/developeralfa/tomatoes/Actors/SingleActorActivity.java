package com.example.developeralfa.tomatoes.Actors;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Actors.Retrofit.Person;
import com.example.developeralfa.tomatoes.Actors.Retrofit.ResultPeopleMovie;
import com.example.developeralfa.tomatoes.Movies.*;
import com.example.developeralfa.tomatoes.Movies.RecyclerAdapter;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.Movies.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class SingleActorActivity extends AppCompatActivity {
    ImageView poster,backdrop;
    TextView title,overview;
    int id;
    RecyclerView movies;
    Person person;
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    com.example.developeralfa.tomatoes.Movies.RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_actor);

        id = getIntent().getIntExtra("id",819);
        overview = findViewById(R.id.overview);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.title);
        movies = findViewById(R.id.movies);
        recyclerAdapter = new RecyclerAdapter(this,movieArrayList);
        movies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        movies.setAdapter(recyclerAdapter);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        com.example.developeralfa.tomatoes.Actors.Retrofit.RFinterface rFinterface = retrofit.create(com.example.developeralfa.tomatoes.Actors.Retrofit.RFinterface.class);
        Call<ResultPeopleMovie> resultPeopleMovieCall = rFinterface.getPersonsMovies(id+"",KEY);
        resultPeopleMovieCall.enqueue(new Callback<ResultPeopleMovie>() {
            @Override
            public void onResponse(Call<ResultPeopleMovie> call, Response<ResultPeopleMovie> response) {
                movieArrayList.clear();
                movieArrayList.addAll(response.body().getCast());
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movieArrayList.get(0).getBackdrop_path()).into(backdrop);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResultPeopleMovie> call, Throwable t) {

            }
        });

        Call<Person> personCall = rFinterface.getPersonDetails(id+"",KEY);
        personCall.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                person = response.body();
                title.setText(person.getName());
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+person.getProfile_path()).into(poster);
                overview.setText(person.getBiography());

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
