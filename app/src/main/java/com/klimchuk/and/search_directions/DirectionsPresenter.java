package com.klimchuk.and.search_directions;

import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.and.ANDApiLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public class DirectionsPresenter implements DirectionsContract.Presenter {

    private DirectionsContract.View mView;

    DirectionsPresenter(DirectionsContract.View view) {
        mView = view;

    }

    @Override
    public void startSearch(String tag) {
        try {
            ANDApiLoader.getPlacesByTag(tag, new LoadingCallback<List<Place>>() {
                @Override
                public void onPlaceLoaded(List<Place> places) {

                }

                @Override
                public void onLoadingFailed() {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
