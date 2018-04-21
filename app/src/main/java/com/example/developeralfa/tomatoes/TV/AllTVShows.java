package com.example.developeralfa.tomatoes.TV;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developeralfa.tomatoes.Movies.Genres.RecyclerAdapter;
import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.Genre;
import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.ResultGenre;
import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.TV.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.TV.Retrofit.Season;
import com.example.developeralfa.tomatoes.TV.Retrofit.TVShow;
import com.example.developeralfa.tomatoes.TV.Retrofit.TVres;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class AllTVShows extends AppCompatActivity implements RecyclerAdapter.OnGenreClicked {
    RecyclerView genres, popular,favorites;
    RecyclerAdapter recyclerAdapterGenre;
    com.example.developeralfa.tomatoes.TV.RecyclerAdapter recyclerAdapterShows;
    TextView genreName;
    ArrayList<Genre> genre = new ArrayList<>();
    Retrofit retrofit;
    ArrayList<TVShow> popularlist = new ArrayList<>();
    ArrayList<TVShow> genreList = new ArrayList<>();
    RFinterface rFinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tvshows);

        genres = findViewById(R.id.Genres);
        popular = findViewById(R.id.Popular);
        genreName = findViewById(R.id.genreText);
        favorites = findViewById(R.id.Favourites);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        recyclerAdapterGenre = new RecyclerAdapter(this,genre,this);
        getGenres();
        genres.setAdapter(recyclerAdapterGenre);
        genres.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL,false));
        recyclerAdapterShows = new com.example.developeralfa.tomatoes.TV.RecyclerAdapter(this,popularlist);
        popular.setAdapter(recyclerAdapterShows);
        popular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        getPopular();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onGenreClickedListener(int id, final String genre) {
        Call<TVres> tVresCall = rFinterface.getTVGenre(KEY,id+"");
        tVresCall.enqueue(new Callback<TVres>() {
            @Override
            public void onResponse(Call<TVres> call, Response<TVres> response) {
                popularlist.clear();
                popularlist.addAll(response.body().getResults());
                recyclerAdapterShows.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVres> call, Throwable t) {

            }
        });

    }
    void getGenres()
    {
        Call<ResultGenre> resultGenreCall = rFinterface.getGenres(KEY);
        resultGenreCall.enqueue(new Callback<ResultGenre>() {
            @Override
            public void onResponse(Call<ResultGenre> call, Response<ResultGenre> response) {
                genre.clear();
                genre.addAll(response.body().getGenres());
                recyclerAdapterGenre.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResultGenre> call, Throwable t) {

            }
        });
    }
    void getPopular()
    {
        Call<TVres> tVresCall = rFinterface.getPopular(KEY);
        tVresCall.enqueue(new Callback<TVres>() {
            @Override
            public void onResponse(Call<TVres> call, Response<TVres> response) {
                popularlist.clear();
                popularlist.addAll(response.body().getResults());
                recyclerAdapterShows.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVres> call, Throwable t) {
                Log.d("erwe",t.getMessage());

            }
        });
    }
}
