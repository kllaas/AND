package com.klimchuk.and.data.and;

import android.nfc.Tag;

import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alexey on 13.05.17.
 */

public interface ANDApi {

    @GET("/locations")
    Call<JsonArray> getAllPlaces();

    @GET("/tags")
    Call<List<Tag>> getAllTags();

    @GET("/tags/{tag_id}/locations")
    Call<List<Tag>> getPlacesByLoaction(@Path("tag_id") int tagId);

    @GET("/locations/{location_id}/posts")
    Call<List<Tag>> getPostsFromLocation(@Path("location_id") String locationId);

}