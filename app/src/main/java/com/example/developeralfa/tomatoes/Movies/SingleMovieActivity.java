package com.example.developeralfa.tomatoes.Movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Actors.Retrofit.Person;
import com.example.developeralfa.tomatoes.Movies.Retrofit.CastResult;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.Movies.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Rate;
import com.example.developeralfa.tomatoes.Movies.Retrofit.RateReq;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Result;
import com.example.developeralfa.tomatoes.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class SingleMovieActivity extends AppCompatActivity {
    ImageView poster,backdrop;
    TextView title,overview;
    RecyclerView similar,actors;
    RecyclerAdapter recyclerAdapter;
    com.example.developeralfa.tomatoes.Actors.RecyclerAdapter recyclerAdapterActor;
    RFinterface rFinterface;
    SharedPreferences sharedPreferences;
    String token=null;
    RatingBar ratingBar;
    CastResult castResult;
    ArrayList<Person> people = new ArrayList<>();
    ArrayList<Movie> simList = new ArrayList<>();
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        final int id;
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        similar = findViewById(R.id.similar);
        actors = findViewById(R.id.actors);
        recyclerAdapter = new RecyclerAdapter(this,simList);
        recyclerAdapterActor = new com.example.developeralfa.tomatoes.Actors.RecyclerAdapter(this,people);
        ratingBar = findViewById(R.id.rating);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        if(sharedPreferences.getBoolean("loggedIn",false)) token = sharedPreferences.getString("access",null);
        else if(sharedPreferences.getBoolean("isGuest",false)) token = sharedPreferences.getString("access",null);



        similar.setAdapter(recyclerAdapter);
        similar.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        actors.setAdapter(recyclerAdapterActor);
        actors.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        id = getIntent().getIntExtra("id",33123);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        final Call<Movie> movieCall = rFinterface.getMovieDetails(id+"",KEY);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie = response.body();
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path()).into(poster);
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.getBackdrop_path()).into(backdrop);
                title.setText(movie.getName());
                overview.setText(movie.getOverview());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
        Call<Result> resultCall =rFinterface.getSimilarMovies(id+"",KEY);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                simList.clear();
                simList.addAll(response.body().getResults());
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        Call<CastResult> castResultCall = rFinterface.getMovieCast(id+"",KEY);
        castResultCall.enqueue(new Callback<CastResult>() {
            @Override
            public void onResponse(Call<CastResult> call, Response<CastResult> response) {
                people.clear();
                people.addAll(response.body().getCast());
                recyclerAdapterActor.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CastResult> call, Throwable t) {
                Log.d("error",t.getMessage());

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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(final RatingBar ratingBar, final float rating, boolean fromUser) {

                if(token!=null) {
                    Call<Rate> rateCall = rFinterface.rateMovie(id+"",KEY,token,new RateReq(rating));
                    rateCall.enqueue(new Callback<Rate>() {
                        @Override
                        public void onResponse(Call<Rate> call, Response<Rate> response) {
                            Snackbar.make(ratingBar,"Rated "+rating+" as "+sharedPreferences.getString("username","Guest"),Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Rate> call, Throwable t) {

                        }
                    });


                }
                else Snackbar.make(ratingBar,"Not Logged In",Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }

}
