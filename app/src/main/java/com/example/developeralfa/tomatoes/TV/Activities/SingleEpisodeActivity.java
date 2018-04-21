package com.example.developeralfa.tomatoes.TV.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.TV.Retrofit.Episode;
import com.example.developeralfa.tomatoes.TV.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.TV.Retrofit.Season;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class SingleEpisodeActivity extends AppCompatActivity {



    ImageView poster,backdrop;
    TextView name,overview;
    int id,season_num,episode_num;
    Retrofit retrofit;
    RFinterface rFinterface;
    Episode episode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_episode);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        name = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        id = getIntent().getIntExtra("id",0);
        season_num = getIntent().getIntExtra("season",1);
        episode_num = getIntent().getIntExtra("episode",2);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        Call<Episode> call = rFinterface.getEpisodes(id+"",season_num+"",episode_num+"",KEY);
        call.enqueue(new Callback<Episode>() {
            @Override
            public void onResponse(Call<Episode> call, Response<Episode> response) {
                episode = response.body();

                overview.setText(episode.getOverview());
                name.setText(episode.getName());

                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+episode.getStill_path()).into(poster);


            }

            @Override
            public void onFailure(Call<Episode> call, Throwable t) {

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
