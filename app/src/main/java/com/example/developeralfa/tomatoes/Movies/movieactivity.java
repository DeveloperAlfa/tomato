package com.example.developeralfa.tomatoes.Movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.Genre;
import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.ResultGenre;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.Movies.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Result;
import com.example.developeralfa.tomatoes.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class movieactivity extends AppCompatActivity implements com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter.OnGenreClicked {
    ArrayList<Movie> movies=new ArrayList<>();
    ArrayList<Movie> movies2=new ArrayList<>();
    RFinterface rFinterface;
    TextView textView;
    RecyclerAdapter recyclerAdapter,recyclerAdapter2;
    com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter recyclerAdapterGenres;
    com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.RFinterface rFinterfaceGenres;
    ArrayList<Genre> genresArrayList = new ArrayList<>();
    RecyclerView genres,now,popular,favorites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = findViewById(R.id.genreText);
        genres = findViewById(R.id.Genres);
        now = findViewById(R.id.Now);
        popular = findViewById(R.id.Popular);
        favorites = findViewById(R.id.Favourites);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        Call<Result> listCall = rFinterface.getPopularMovies(KEY);
        Call<Result> listCall1 = rFinterface.getHindiMovies(KEY,"hi");
        popular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        now.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerAdapter = new RecyclerAdapter(this,movies);
        recyclerAdapter2 = new RecyclerAdapter(this,movies2);
        recyclerAdapterGenres = new com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter(this,genresArrayList,this);
        genres.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        genres.setAdapter(recyclerAdapterGenres);
        Retrofit retrofit1 = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        rFinterfaceGenres = retrofit1.create(com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.RFinterface.class);
        Call<ResultGenre> resultGenreCall = rFinterfaceGenres.getAllGenres(KEY);
        resultGenreCall.enqueue(new Callback<ResultGenre>() {
            @Override
            public void onResponse(Call<ResultGenre> call, Response<ResultGenre> response) {
                genresArrayList.clear();
                genresArrayList.addAll(response.body().getGenres());
                recyclerAdapterGenres.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResultGenre> call, Throwable t) {

            }
        });

        popular.setAdapter(recyclerAdapter);
        now.setAdapter(recyclerAdapter2);

        listCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("no","po");
                movies.clear();
                movies.addAll(response.body().getResults());
                recyclerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("a",t.getMessage());
            }
        });
        listCall1.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                movies2.clear();
                movies2.addAll(response.body().getResults());
                recyclerAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });





    }


    @Override
    public void onGenreClickedListener(int id, final String genre) {
        Call<Result> resultCall  = rFinterfaceGenres.getGenreMovies(KEY,id+"");
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                movies.clear();
                textView.setText(genre);
                movies.addAll(response.body().getResults());
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
