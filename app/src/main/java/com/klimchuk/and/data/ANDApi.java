package com.klimchuk.and.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexey on 13.05.17.
 */

public interface ANDApi {

    @GET("/maps/api/place/nearbysearch/json")
    Call<JsonObject> getAllPlaces();

}
