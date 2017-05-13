package com.klimchuk.and.maps;

import android.content.Context;

import com.klimchuk.and.adapter.RecyclerAdapter;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public interface MapsContract {

    interface View {

        void showMarkers(List<MarkerViewOptions> markers);

        Context getActivityContext();

        void showPlace(RecyclerAdapter recyclerAdapter);
    }

    interface Presenter {

        MapboxMap.OnMarkerClickListener getOnMarkerClick();

        void onSearch();
    }

}
