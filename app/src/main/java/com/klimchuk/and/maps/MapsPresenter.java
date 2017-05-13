package com.klimchuk.and.maps;

import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;
import java.util.Random;

import static com.klimchuk.and.maps.MapsContract.Presenter;
import static com.klimchuk.and.maps.MapsContract.View;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsPresenter implements Presenter {

    private View mView;

    MapsPresenter(View view) {
        mView = view;

        loadMarkers();
    }

    private void loadMarkers() {
        ArrayList<MarkerViewOptions> markers = new ArrayList<>();

        float minX = -0.5f;
        float maxX = 0.5f;

        Random rand = new Random();

        for (int i = 0; i < 200; i++) {
            float finalX = rand.nextFloat() * (maxX - minX) + minX;
            markers.add(new MarkerViewOptions()
                    .position(new LatLng(-33.85699436 + finalX, 151.21510684 - finalX)));
        }

        mView.showMarkers(markers);
    }

    @Override
    public MapboxMap.OnMarkerClickListener getOnMarkerClick() {
        return marker -> {
            loadPlace(marker.getPosition());
            return true;
        };
    }

    private void loadPlace(LatLng position) {
        Toast.makeText(mView.getActivityContext(), "Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearch() {

    }
}
