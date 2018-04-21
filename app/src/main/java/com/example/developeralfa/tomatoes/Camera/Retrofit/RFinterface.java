package com.example.developeralfa.tomatoes.Camera.Retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 21-04-2018.
 */

public interface RFinterface {
    @POST("https://vision.googleapis.com/v1/images:annotate")
    Call<Res> getImagedetails(@Query("key") String key, @Body Req body);
}
