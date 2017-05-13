package com.klimchuk.and.data;

import android.content.Context;

import com.google.gson.JsonObject;
import com.klimchuk.and.App;
import com.klimchuk.and.R;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexey on 13.05.17.
 */

public class GoogleAPILoader {

    public static void getPlaceByPosition(Context context, LatLng latLng, LoadingCallback<Place> callback) throws IOException, JSONException {
        App.getPlacesApi()
                .getPlaces(context.getString(R.string.google_api_key), context.getString(R.string.request_types),
                        latLng.getLatitude() + "," + latLng.getLongitude(), "500")
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            callback.onPlaceLoaded(parsePlace(response.body().toString()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        callback.onLoadingFailed();
                    }
                });
    }

    private static Place parsePlace(String response) throws JSONException {
        JSONObject root = new JSONObject(response);
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
        }

        // TODO: put real posts count here
        return new Place(name, address, new LatLng(lat, lng), 1256, photo);
    }

}
