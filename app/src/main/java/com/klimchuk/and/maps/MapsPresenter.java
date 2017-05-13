package com.klimchuk.and.maps;

import android.widget.Toast;

import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.PlacesApi;
import com.klimchuk.and.data.PlacesLoader;
import com.klimchuk.and.maps.MapsContract.View;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.klimchuk.and.maps.MapsContract.Presenter;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsPresenter implements Presenter {

    private View mView;

    private RecyclerAdapter mAdapter;

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
                    .position(new LatLng(-33.85699436 + finalX, 151.21510684 + finalX)));
        }

        mView.showMarkers(markers);
    }

    @Override
    public MapboxMap.OnMarkerViewClickListener getOnMarkerClick() {
        return (marker, view, adapter) -> {
            loadPlace(marker.getPosition());
            return false;
        };
    }

    private void loadPlace(LatLng position) {
        try {

            PlacesLoader.getPlaceByPosition(mView.getActivityContext(), position,
                    new PlacesApi.LoadingPlaceCallback() {
                        @Override
                        public void onPlaceLoaded(Place place) {
                            List<InstaPost> posts = new ArrayList<>();
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());
                            posts.add(new InstaPost());

                            mView.showPlace(getRecyclerAdapter(place, posts));

//                            PlacesLoader.getPhoto();
                        }

                        @Override
                        public void onLoadingFailed() {
                            Toast.makeText(mView.getActivityContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private RecyclerAdapter getRecyclerAdapter(Place place, List<InstaPost> posts) {
        mAdapter = new RecyclerAdapter(posts, mView.getActivityContext(), place);
        return mAdapter;
    }

    @Override
    public void onSearch() {

    }
}