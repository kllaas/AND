package com.klimchuk.and.data;

import com.google.gson.JsonObject;
import com.klimchuk.and.App;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexey on 13.05.17.
 */

public class ANDApiLoader {

    public static void getAllPlaces(LoadingCallback<List<Place>> callback) {
        App.getAndApi().getAllPlaces().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                callback.onPlaceLoaded(parsePlaces(response.body().toString()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }

    private static List<Place> parsePlaces(String response) {
/*        JSONObject root = new JSONObject(response);
        System.out.println(response);

        JSONArray mainArray = root.getJSONArray("results");

        JSONObject place = mainArray.getJSONObject(0);

        JSONObject location = place.getJSONObject("geometry").getJSONObject("location");
        float lat = Float.parseFloat(location.getString("lat"));
        float lng = Float.parseFloat(location.getString("lng"));

        String name = place.getString("name");
        String address = place.getString("vicinity");

        String photo = null;
        if (place.has("photos")) {
            photo = place.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
        }*/

        return null;
    }

}
