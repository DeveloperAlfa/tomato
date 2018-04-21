package com.example.developeralfa.tomatoes.Movies.Retrofit;

import com.example.developeralfa.tomatoes.Actors.Retrofit.ResultPeopleMovie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 17-04-2018.
 */

public interface RFinterface {
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails( @Path(value = "movie_id") String movie_id,@Query("api_key") String apiKey);
    @GET("movie/{movie_id}/similar")
    Call<Result> getSimilarMovies(@Path(value = "movie_id") String movie_id,@Query("api_key") String apiKey);
    @POST("/movie/{movie_id}/rating")
    @Headers({"Content-type:application/json;charset=utf-8"})
    Call<Rate> rateMovie(@Path(value = "movie_id") String movie_id,@Query("api_key") String apiKey,  @Query("session_id") String session, @Body RateReq rateReq);
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
    @GET("discover/movie")
    Call<Result> getHindiMovies(@Query("api_key") String apiKey, @Query("with_original_language") String language);
    @GET("movie/{movie_id}/credits")
    Call<CastResult> getMovieCast(@Path(value = "movie_id") String movie_id, @Query("api_key") String apiKey);
}
