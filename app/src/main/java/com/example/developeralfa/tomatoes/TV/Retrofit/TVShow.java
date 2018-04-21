package com.example.developeralfa.tomatoes.TV.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.Genre;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class TVShow {
    String backdrop_path;
    int id;
    ArrayList<Genre> genres;
    int number_of_episodes;
    int number_of_seasons;
    String overview;
    String poster_path;
    String name;

    public String getName() {
        return name;
    }

    public int getId() {

        return id;
    }

    ArrayList<Season> seasons;
    String vote_average;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public String getVote_average() {
        return vote_average;
    }
}
