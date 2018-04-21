package com.example.developeralfa.tomatoes.Movies.Genres.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Retrofit.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 19-04-2018.
 */

public interface RFinterface {
    @GET("genre/movie/list")
    Call<ResultGenre> getAllGenres(@Query("api_key") String apiKey);
    @GET("discover/movie")
    Call<Result> getGenreMovies(@Query("api_key") String apiKey, @Query("with_genres") String genre);

}
