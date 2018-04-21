package com.example.developeralfa.tomatoes.Actors.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;

/**
 * Created by Developer Alfa on 19-04-2018.
 */

public class Person {
    int id;
    String name;
    String profile_path;
    String biography;
    Movie known_for;

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public Movie getKnown_for() {
        return known_for;
    }

    public int getId() {

        return id;
    }

    public String getBiography() {
        return biography;
    }
}
