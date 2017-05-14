package com.klimchuk.and.search;

import android.widget.Toast;

import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.and.ANDApiLoader;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;

    SearchPresenter(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void startSearch(String tag) {
        try {

            ANDApiLoader.getPlacesByTag(tag, new LoadingCallback<List<Place>>() {
                @Override
                public void onPlaceLoaded(List<Place> places) {
                    if (places != null && places.size() != 0) {
                        for (Place place : places) {
                            place.setLatLng(new LatLng(place.getLongitude(), place.getLongitude()));
                        }

                        mView.onSearch(places);
                    }
                }

                @Override
                public void onLoadingFailed() {
                    Toast.makeText(mView.getAppContext(), "Loading error", Toast.LENGTH_SHORT);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
