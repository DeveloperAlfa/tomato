package com.example.developeralfa.tomatoes.TV.Retrofit;

import com.example.developeralfa.tomatoes.Actors.Retrofit.Person;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public class Episode {
    ArrayList<Person> crew;
    int episode_number;
    String name;
    String overview;
    int id;
    int season_number;
    String vote_average;
    String still_path;

    public ArrayList<Person> getCrew() {
        return crew;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public int getSeason_number() {
        return season_number;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getStill_path() {
        return still_path;
    }
}
