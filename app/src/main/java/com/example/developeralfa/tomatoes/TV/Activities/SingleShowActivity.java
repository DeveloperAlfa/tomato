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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.R;
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

public class SingleShowActivity extends AppCompatActivity implements RecyclerAdapter.OnViewClicked {

    RecyclerView seasons;
    ArrayList<Season> seasonArrayList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    ImageView poster,backdrop;
    TextView name,overview;
    int id;
    Retrofit retrofit;
    RFinterface rFinterface;
    TVShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_show);
        seasons = findViewById(R.id.seasons);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        name = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        recyclerAdapter = new RecyclerAdapter(this,seasonArrayList,this);
        seasons.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL,false));
        seasons.setAdapter(recyclerAdapter);
        id = getIntent().getIntExtra("id",33213);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        getShow();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getShow() {
        Call<TVShow> call = rFinterface.getShowdetails(id+"",KEY);
        call.enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(Call<TVShow> call, Response<TVShow> response) {
                tvShow = response.body();
                name.setText(tvShow.getName());
                overview.setText(tvShow.getOverview());
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tvShow.getPoster_path()).into(poster);
                Picasso.get().load("https://image.tmdb.org/t/p/w500/"+tvShow.getBackdrop_path()).into(backdrop);
                seasonArrayList.clear();
                seasonArrayList.addAll(tvShow.getSeasons());
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<TVShow> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClicked(int id1) {
        Intent intent = new Intent(this, SingleSeasonActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("season",id1);
        startActivity(intent);
    }
}
