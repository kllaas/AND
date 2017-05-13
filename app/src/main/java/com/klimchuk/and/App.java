package com.klimchuk.and;

import android.app.Application;

import com.klimchuk.and.data.PlacesApi;
import com.mapbox.mapboxsdk.Mapbox;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexey on 13.05.17.
 */

public class App extends Application{

    private static final String BASE_URL = "http://e9662d37.ngrok.io";
    private static final String PLACES_BASE_URL = "https://maps.googleapis.com";

    private static PlacesApi placesApi;

    public static PlacesApi getPlacesApi() {
        return placesApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(this, "pk.eyJ1IjoicGlla2llIiwiYSI6ImNqMm4wZHh5YjAwMjMzM3BhMHNwdWo4aXYifQ.g4Z2AZ6Mvnq59QkJniZ69A");

        placesApi = getPlacesRetrofitInstance().create(PlacesApi.class);
    }

    public Retrofit getANDRetrofitInstance() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
    }

    public Retrofit getPlacesRetrofitInstance() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(PLACES_BASE_URL).build();
    }
}
