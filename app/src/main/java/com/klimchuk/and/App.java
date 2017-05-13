package com.klimchuk.and;

import android.app.Application;

import com.mapbox.mapboxsdk.Mapbox;

import retrofit2.Retrofit;

/**
 * Created by alexey on 13.05.17.
 */

public class App extends Application{

    private static final String BASE_URL = "http://e9662d37.ngrok.io";
    private static final String PLACES_BASE_URL = "https://maps.googleapis.com/maps/api";

    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(this, "pk.eyJ1IjoicGlla2llIiwiYSI6ImNqMm4wZHh5YjAwMjMzM3BhMHNwdWo4aXYifQ.g4Z2AZ6Mvnq59QkJniZ69A");

    }

    public Retrofit getFlavouredInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL).build();
    }

    public Retrofit getOtherBaseUrl() {
        return new Retrofit.Builder().baseUrl(PLACES_BASE_URL).build();
    }
}
