package com.klimchuk.and.maps;

import android.content.Context;

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
    }

    interface Presenter {

        MapboxMap.OnMarkerClickListener getOnMarkerClick();

        void onSearch();
    }

}
