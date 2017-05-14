package com.klimchuk.and.search;

import android.widget.Toast;

import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.Tag;
import com.klimchuk.and.data.and.ANDApiLoader;
import com.klimchuk.and.data.source.StaticDataCache;
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

        try {

            loadTags();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTags() throws IOException {
        ANDApiLoader.getAllTags(new LoadingCallback<List<Tag>>() {
            @Override
            public void onPlaceLoaded(List<Tag> tags) {
                StaticDataCache.tags = tags;
                mView.setAdapter(StaticDataCache.getTagsStringArray());
            }

            @Override
            public void onLoadingFailed() {

            }
        });
    }

    @Override
    public void startSearch(String tag) {
        try {
            ANDApiLoader.getPlacesByTag(tag, new LoadingCallback<List<Place>>() {
                @Override
                public void onPlaceLoaded(List<Place> places) {
                    if (places != null && places.size() != 0) {

                        Toast.makeText(mView.getAppContext(), "Place size: " + places.size(), Toast.LENGTH_SHORT);
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
