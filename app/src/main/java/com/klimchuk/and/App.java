package com.klimchuk.and;

import android.app.Application;

import com.mapbox.mapboxsdk.Mapbox;

/**
 * Created by alexey on 13.05.17.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(this, "pk.eyJ1IjoicGlla2llIiwiYSI6ImNqMm4wZHh5YjAwMjMzM3BhMHNwdWo4aXYifQ.g4Z2AZ6Mvnq59QkJniZ69A");
    }
}
