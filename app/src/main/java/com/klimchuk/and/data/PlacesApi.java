package com.klimchuk.and.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexey on 13.05.17.
 */

public interface PlacesApi {

    @GET("/maps/api/place/nearbysearch/json")
    Call<JsonObject> getPlaces(@Query("key") String key,
                               @Query("type") String type,
                               @Query("location") String location,
                               @Query("radius") String radius);

    @GET("/maps/api/place/photo/")
    Call<JsonObject> getPhoto(@Query("key") String key,
                              @Query("maxwidth") String maxWidth,
                              @Query("photoreference") String photoReference);

    interface LoadingPlaceCallback {

        void onPlaceLoaded(Place place);

        void onLoadingFailed();

    }

    interface LoadingPhotoCallback {

        void onPlaceLoaded(String photo);

        void onLoadingFailed();

    }
}
