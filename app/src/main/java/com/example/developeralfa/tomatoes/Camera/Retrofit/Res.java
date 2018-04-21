package com.example.developeralfa.tomatoes.Camera.Retrofit;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 21-04-2018.
 */

public class Res {
    public ArrayList<Response> getResponses() {
        return responses;
    }

    public Res(ArrayList<Response> responses) {

        this.responses = responses;
    }

    ArrayList<Response> responses;
}
