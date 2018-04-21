package com.example.developeralfa.tomatoes.TV.Retrofit;

import com.example.developeralfa.tomatoes.Actors.Retrofit.Person;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class Season {
    ArrayList<Episode> episodes;
    ArrayList<Person> crew;
    String vote_average;

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public ArrayList<Person> getCrew() {
        return crew;
    }

    public String getVote_average() {
        return vote_average;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    int id;
    String name;
    String overview;
    String poster_path;
    int season_number;

}
