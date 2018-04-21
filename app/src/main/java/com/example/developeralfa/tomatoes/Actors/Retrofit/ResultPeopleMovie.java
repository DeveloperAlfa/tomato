package com.example.developeralfa.tomatoes.Actors.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 19-04-2018.
 */

public class ResultPeopleMovie {
    public ArrayList<Movie> getCast() {
        return cast;
    }

    ArrayList<Movie> cast;
}
