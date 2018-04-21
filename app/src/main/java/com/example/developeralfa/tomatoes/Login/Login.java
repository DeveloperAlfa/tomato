package com.example.developeralfa.tomatoes.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.developeralfa.tomatoes.Login.RetrofitLogin.Guest;
import com.example.developeralfa.tomatoes.Login.RetrofitLogin.RFinterface;
import com.example.developeralfa.tomatoes.Login.RetrofitLogin.Token;
import com.example.developeralfa.tomatoes.Login.RetrofitLogin.User;
import com.example.developeralfa.tomatoes.MainActivity;
import com.example.developeralfa.tomatoes.Movies.Retrofit.Movie;
import com.example.developeralfa.tomatoes.Movies.movieactivity;
import com.example.developeralfa.tomatoes.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    public static final String KEY = "5f300c4743513850b463c920e3d1b6d0";
    Token token = null;
    User user = null;
    Guest guest = null;
    EditText u,p;
    RFinterface rFinterface;
    SharedPreferences sharedPreferences;

    String username="developeralfa",password="nipunDTU899";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u = findViewById(R.id.u);
        p = findViewById(R.id.p);

        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/").build();
        rFinterface = retrofit.create(RFinterface.class);
        getToken();
        if(sharedPreferences.getBoolean("loggedIn",false)) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }





    }

    private void guestSession() {

        final Call<Guest> guestCall = rFinterface.guest(KEY);
        guestCall.enqueue(new Callback<Guest>() {
            @Override
            public void onResponse(Call<Guest> call, Response<Guest> response) {
                guest = response.body();
                sharedPreferences.edit().putString("access",guest.getGuest_session_id()).putString("username","Guest").putBoolean("loggedIn",false).putBoolean("isGuest",true).apply();
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Guest> call, Throwable t) {

            }
        });
    }

    void getToken()
    {
        Call<Token> tokenCall = rFinterface.getToken(KEY);
        tokenCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.d("token",response.body().getRequest_token());
                token = response.body();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void login()
    {

        username = u.getText().toString();
        password = p.getText().toString();
        Call<User> userCall = rFinterface.login(KEY,username,password,token.getRequest_token());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                if(user!=null) {
                    Log.d("a", user.getRequest_token());
                    sharedPreferences.edit().putString("access", user.getRequest_token()).putString("username", username).putBoolean("loggedIn",true).putBoolean("isGuest",false).apply();
                    startActivity(new Intent(Login.this, movieactivity.class));
                    finish();
                }
                else Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login.this, "Internet Connectivity Problems", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void login(View view) {
        login();
    }

    public void guest(View view) {
        guestSession();
    }
}
