package com.klimchuk.and.search_directions;

import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.and.ANDApiLoader;
import com.klimchuk.and.domain.Route;

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
    public void startSearch(String lat1, String lng1, String lat2, String lng2) {
        try {
            ANDApiLoader.getRoute(lat1, lng1, lat2, lng2, new LoadingCallback<Route>() {
                @Override
                public void onPlaceLoaded(Route route) {
                    mView.showRoute(route);
                }

                @Override
                public void onLoadingFailed() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
