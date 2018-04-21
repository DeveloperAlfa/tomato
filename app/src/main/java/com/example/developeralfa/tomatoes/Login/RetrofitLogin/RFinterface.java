package com.example.developeralfa.tomatoes.Login.RetrofitLogin;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

/**
 * Created by Developer Alfa on 17-04-2018.
 */

public interface RFinterface {

    @GET("authentication/token/new")
    Call<Token> getToken(@Query("api_key") String apiKey);
    @GET("authentication/token/validate_with_login")
    Call<User> login(@Query("api_key") String apiKey,@Query("username") String username,@Query("password") String password,@Query("request_token") String request_token);
    @GET("authentication/guest_session/new")
    Call<Guest> guest(@Query("api_key") String apiKey);
}
