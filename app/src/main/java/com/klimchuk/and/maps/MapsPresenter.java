package com.klimchuk.and.maps;

import android.widget.Toast;

import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.and.ANDApiLoader;
import com.klimchuk.and.maps.MapsContract.View;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.klimchuk.and.maps.MapsContract.Presenter;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsPresenter implements Presenter {

    private View mView;

    private RecyclerAdapter mAdapter;

    private Map<Long, Place> mPlaces = new HashMap<>();

    MapsPresenter(View view) {
        mView = view;

        loadMarkers();
    }

    private void loadMarkers() {
        ANDApiLoader.getAllPlaces(new LoadingCallback<List<Place>>() {
            @Override
            public void onPlaceLoaded(List<Place> places) {
                configureMarkers(places);
            }

            @Override
            public void onLoadingFailed() {

            }
        });
    }

    private void configureMarkers(List<Place> places) {
        mView.clearMarkers();
        mPlaces = new HashMap<>();

        ArrayList<MarkerViewOptions> markers = new ArrayList<>();

        for (Place place : places) {
            MarkerViewOptions marker = new MarkerViewOptions()
                    .position(place.getLatLng());

            markers.add(marker);
        }

        mView.showMarkers(markers);

        for (int i = 0; i < mView.getMarkers().size(); i++) {
            mPlaces.put(mView.getMarkers().get(i).getId(), places.get(i));
        }

        mView.moveToBounds(mView.getMarkers());
    }

    @Override
    public MapboxMap.OnMarkerViewClickListener getOnMarkerClick() {
        return (marker, view, adapter) -> {
            loadInstaPosts(marker);
            return false;
        };
    }

    private void loadInstaPosts(Marker marker) {
        try {
            ANDApiLoader.getPostsByLocation(mPlaces.get(marker.getId()).getId(),
                    new LoadingCallback<List<InstaPost>>() {
                        @Override
                        public void onPlaceLoaded(List<InstaPost> posts) {

                            mView.showPlace(getRecyclerAdapter(mPlaces.get(marker.getId()), posts));
                        }

                        @Override
                        public void onLoadingFailed() {
                            Toast.makeText(mView.getActivityContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RecyclerAdapter getRecyclerAdapter(Place place, List<InstaPost> posts) {
        mAdapter = new RecyclerAdapter(place, posts, mView.getActivityContext());
        return mAdapter;
    }

    @Override
    public void onSearch(List<Place> places) {
        configureMarkers(places);
    }
}