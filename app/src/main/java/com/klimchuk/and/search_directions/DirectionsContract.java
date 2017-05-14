package com.klimchuk.and.search_directions;

import android.content.Context;

import com.klimchuk.and.domain.Route;

/**
 * Created by alexey on 13.05.17.
 */

public interface DirectionsContract {

    interface View {

        Context getAppContext();

        void showRoute(Route route);
    }

    interface Presenter {

        void startSearch(String lat1, String lng1, String lat2, String lng2);
    }

}
