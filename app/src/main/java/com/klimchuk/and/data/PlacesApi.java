package com.klimchuk.and.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexey on 13.05.17.
 */

public interface PlacesApi {

    @GET("/place/nearbysearch/json")
    Call<List<Place>> listRepos(@Query("key") String key,
                                @Query("location") String location,
                                @Query("radius") String radius);
}
