package com.example.developeralfa.tomatoes.Login.RetrofitLogin;

/**
 * Created by Developer Alfa on 17-04-2018.
 */

public class User {
    boolean success;
    String request_token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
