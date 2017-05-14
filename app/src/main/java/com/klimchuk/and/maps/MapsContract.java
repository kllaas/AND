package com.klimchuk.and.maps;

import android.content.Context;

import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.Place;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public interface MapsContract {

    interface View {

        void showMarkers(List<MarkerViewOptions> markers);

        void moveToBounds(List<Marker> p);

        Context getActivityContext();

        void showPlace(RecyclerAdapter recyclerAdapter);

        List<Marker> getMarkers();

        void clearMarkers();

        void setSlidingViewVisibility(boolean visibility);
    }

    interface Presenter {

        void configureMarkers(List<Place> places, boolean shouldMove);

        MapboxMap.OnMarkerViewClickListener getOnMarkerClick();

        void onSearch(List<Place> places);
    }

}
