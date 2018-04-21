package com.example.developeralfa.tomatoes.Actors.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 19-04-2018.
 */

public interface RFinterface {
    @GET("person/{person_id}")
    Call<Person> getPersonDetails(@Path(value = "person_id") String movie_id, @Query("api_key") String apiKey);
    @GET("person/popular")
    Call<People> getPopularPeople( @Query("api_key") String apiKey);
    @GET("person/{person_id}/combined_credits")
    Call<ResultPeopleMovie> getPersonsMovies(@Path(value = "person_id") String movie_id, @Query("api_key") String apiKey);
}
