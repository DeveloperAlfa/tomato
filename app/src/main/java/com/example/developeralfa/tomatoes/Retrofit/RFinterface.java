package com.example.developeralfa.tomatoes.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 20-04-2018.
 */

public interface RFinterface {
    @GET("search/multi")
    Call<object> getResult(@Query("api_key") String apiKey, @Query("query") String query);
}
