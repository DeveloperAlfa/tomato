package com.example.developeralfa.tomatoes.Movies.Retrofit;

/**
 * Created by Developer Alfa on 17-04-2018.
 */

public class RateReq {
    float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public RateReq(float value) {

        this.value = value;
    }
}
