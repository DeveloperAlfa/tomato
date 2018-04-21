package com.example.developeralfa.tomatoes.TV.Retrofit;

import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.Genre;
import com.example.developeralfa.tomatoes.Movies.Genres.Retrofit.ResultGenre;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public interface RFinterface {
@GET("tv/{tv_id}")
    Call<TVShow> getShowdetails(@Path(value = "tv_id") String tv_id, @Query("api_key") String apiKey);
@GET("tv/{tv_id}/season/{season_number}")
    Call<Season> getSeasons(@Path(value = "tv_id") String tv_id,@Path(value = "season_number") String season_number, @Query("api_key") String apiKey);
@GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Call<Episode> getEpisodes(@Path(value = "tv_id") String tv_id,@Path(value = "season_number") String season_number,@Path(value = "episode_number") String episode_number, @Query("api_key") String apiKey);
@GET("tv/popular")
    Call<TVres> getPopular(@Query("api_key") String apiKey);
    @GET("genre/tv/list")
    Call<ResultGenre> getGenres(@Query("api_key") String apiKey);
    @GET("discover/tv")
    Call<TVres> getTVGenre(@Query("api_key") String apiKey,@Query("with_genres") String genre);

}
