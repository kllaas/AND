package com.klimchuk.and.data.and;


import com.google.gson.JsonArray;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.Tag;
import com.klimchuk.and.domain.Route;

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
    Call<List<Place>> getPlacesByTag(@Path("tag_id") String tagId);

    @GET("/locations/{location_id}/posts")
    Call<List<InstaPost>> getPostsFromLocation(@Path("location_id") String locationId);

    @GET("/directions/{lat1},{lng1};{lat2},{lng2}")
    Call<Route> getRoute(@Path("lat1") String lat1,
                         @Path("lng1") String lng1,
                         @Path("lat2") String lat2,
                         @Path("lng2") String lng2);

}