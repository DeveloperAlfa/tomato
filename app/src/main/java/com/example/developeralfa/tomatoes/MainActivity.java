package com.example.developeralfa.tomatoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developeralfa.tomatoes.Camera.Camera;
import com.example.developeralfa.tomatoes.Login.Login;
import com.example.developeralfa.tomatoes.Movies.RecyclerAdapter;
import com.example.developeralfa.tomatoes.Movies.movieactivity;
import com.example.developeralfa.tomatoes.TV.AllTVShows;

public class MainActivity extends AppCompatActivity {
    ImageView poster,backdrop;
    TextView title,overview;
    RecyclerView similar,actors;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tv(View view) {
        startActivity(new Intent(this, movieactivity.class));
    }
    public void camera(View view) {
        startActivity(new Intent(this, Camera.class));
    }
}
