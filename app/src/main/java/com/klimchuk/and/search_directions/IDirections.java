package com.klimchuk.and.search_directions;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by alexey on 14.05.17.
 */

public interface IDirections {

    interface DirectionsCallback {

        void onDirectionsSearch(LatLng[] points);

    }

}
