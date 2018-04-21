package com.example.developeralfa.tomatoes.TV.Activities;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.TV.*;
import com.example.developeralfa.tomatoes.TV.Retrofit.Episode;
import com.example.developeralfa.tomatoes.TV.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.TV.Retrofit.Season;
import com.example.developeralfa.tomatoes.TV.Retrofit.TVShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class SingleSeasonActivity extends AppCompatActivity implements RecyclerAdapterEpisode.OnViewClicked {

    RecyclerView episodes;
    ArrayList<Episode> episodeArrayList = new ArrayList<>();
    RecyclerAdapterEpisode recyclerAdapter;
    ImageView poster,backdrop;
    TextView name,overview;
    int id,season_num;
    Retrofit retrofit;
    RFinterface rFinterface;
    Season season;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_season);
        episodes = findViewById(R.id.episodes);
        recyclerAdapter = new RecyclerAdapterEpisode(this,episodeArrayList,this);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        name = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        episodes.setAdapter(recyclerAdapter);
        episodes.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL,false));

        id = getIntent().getIntExtra("id",0);
        season_num = getIntent().getIntExtra("season",1);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        getSeason();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getSeason() {
        final Call<Season> seasonCall = rFinterface.getSeasons(id+"",season_num+"",KEY);
        seasonCall.enqueue(new Callback<Season>() {
            @Override
            public void onResponse(Call<Season> call, Response<Season> response) {
                season = response.body();
                overview.setText(season.getOverview());
                name.setText(season.getName());
                episodeArrayList.clear();
                episodeArrayList.addAll(season.getEpisodes());
                recyclerAdapter.notifyDataSetChanged();
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+season.getPoster_path()).into(poster);
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+season.getEpisodes().get(0).getStill_path()).into(backdrop);



            }

            @Override
            public void onFailure(Call<Season> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClicked(int id1) {
        Intent intent = new Intent(this, SingleEpisodeActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("season",season_num);
        intent.putExtra("episode",id1);
        startActivity(intent);
    }
}
