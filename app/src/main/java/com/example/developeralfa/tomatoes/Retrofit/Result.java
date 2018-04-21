package com.example.developeralfa.tomatoes.Retrofit;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class Result {
    String media_type;
    String name;
    String poster_path;
    String vote_average;
    int id;
    String title;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getMedia_type() {

        return media_type;
    }
}
