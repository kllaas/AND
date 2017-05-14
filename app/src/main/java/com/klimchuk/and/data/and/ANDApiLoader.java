package com.klimchuk.and.data.and;


import com.google.gson.JsonArray;
import com.klimchuk.and.App;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.Tag;
import com.klimchuk.and.data.source.StaticDataCache;
import com.klimchuk.and.domain.Route;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexey on 13.05.17.
 */

public class ANDApiLoader {

    public static void getAllPlaces(LoadingCallback<List<Place>> callback) {
        App.getAndApi().getAllPlaces().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {

                    StaticDataCache.places = parsePlaces(response.body().toString());
                    callback.onPlaceLoaded(StaticDataCache.places);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    public static void getAllTags(LoadingCallback<List<Tag>> callback) throws IOException {
        Call<List<Tag>> call = App.getAndApi().getAllTags();

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                if (response.body() != null)
                    callback.onPlaceLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    public static void getPostsByLocation(String locationId, LoadingCallback<List<InstaPost>> callback) throws IOException {
        Call<List<InstaPost>> call = App.getAndApi().getPostsFromLocation(locationId);

        call.enqueue(new Callback<List<InstaPost>>() {
            @Override
            public void onResponse(Call<List<InstaPost>> call, Response<List<InstaPost>> response) {
                callback.onPlaceLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<InstaPost>> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    public static void getPlacesByTag(String tag, LoadingCallback<List<Place>> callback) throws IOException {
        Call<List<Place>> call = App.getAndApi().getPlacesByTag(tag);

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                callback.onPlaceLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    public static void getRoute(String lat1, String lng1,
                                String lat2, String lng2, LoadingCallback<Route> callback) throws JSONException {

        Call<Route> call = App.getAndApi().getRoute(lat1, lng1, lat2, lng2);

        call.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                callback.onPlaceLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    private static List<Place> parsePlaces(String response) throws JSONException {
        System.out.println(response);
        List<Place> places = new ArrayList<>();

        JSONArray mainArray = new JSONArray(response);

        for (int i = 0; i < mainArray.length(); i++) {

            JSONObject item = mainArray.getJSONObject(i);

            String id = item.getString("id");
            String name = item.getString("name");

            float lat = Float.parseFloat(item.getString("latitude"));
            float lng = Float.parseFloat(item.getString("longitude"));

            if (Math.abs(lat - 30) < 5)
                System.out.println(id);

            places.add(new Place(id, name, new LatLng(lat, lng)));
        }

        return places;
    }

}
