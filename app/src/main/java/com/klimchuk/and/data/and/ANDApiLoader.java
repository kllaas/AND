package com.klimchuk.and.data.and;

import android.nfc.Tag;

import com.google.gson.JsonArray;
import com.klimchuk.and.App;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

                    System.out.println(response.body().toString());
                    callback.onPlaceLoaded(parsePlaces(response.body().toString()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    public static void getAllTags(LoadingCallback<List<Tag>> callback) {
        Call<List<Tag>> call = App.getAndApi().getAllTags();


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

            places.add(new Place(id, name, new LatLng(lat, lng)));
        }

        return places;
    }

}
