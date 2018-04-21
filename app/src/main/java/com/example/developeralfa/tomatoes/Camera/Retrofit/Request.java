package com.example.developeralfa.tomatoes.Camera.Retrofit;

import java.util.ArrayList;

/**
 * Created by Developer Alfa on 21-04-2018.
 */

public class Request {
    Image image;
    ArrayList<Feature> features;

    public Request(Image image, ArrayList<Feature> features) {
        this.image = image;
        this.features = features;
    }
}
