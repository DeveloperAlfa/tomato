package com.example.developeralfa.tomatoes.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.developeralfa.tomatoes.R;
import com.example.developeralfa.tomatoes.Retrofit.RFinterface;
import com.example.developeralfa.tomatoes.Retrofit.Result;
import com.example.developeralfa.tomatoes.Retrofit.object;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.developeralfa.tomatoes.Login.Login.KEY;

public class Search extends AppCompatActivity {
    EditText editText;
    ArrayList<Result> resultsArr = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.query);
        recyclerView = findViewById(R.id.results);
        recyclerAdapter = new RecyclerAdapter(this,resultsArr);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        if(getIntent().hasExtra("q")) editText.setText(getIntent().getStringExtra("q"));

    }

    public void searchc(View view) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.themoviedb.org/3/").build();
        RFinterface rFinterface = retrofit.create(RFinterface.class);
        Call<object> resultCall = rFinterface.getResult(KEY,editText. getText().toString());
        resultCall.enqueue(new Callback<object>() {
            @Override
            public void onResponse(Call<object> call, Response<object> response) {
                resultsArr.clear();
                resultsArr.addAll(response.body().getResults());
                Log.d("format",resultsArr.get(0).getMedia_type());
                recyclerAdapter.notifyDataSetChanged();
                Log.d("res",resultsArr.toString());

            }

            @Override
            public void onFailure(Call<object> call, Throwable t) {

            }
        });

    }
}
