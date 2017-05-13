package com.klimchuk.and.maps;

import android.widget.Toast;

import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.and.ANDApiLoader;
import com.klimchuk.and.data.google.GoogleAPILoader;
import com.klimchuk.and.maps.MapsContract.View;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        ANDApiLoader.getAllPlaces(new LoadingCallback<List<Place>>() {
            @Override
            public void onPlaceLoaded(List<Place> places) {
                ArrayList<MarkerViewOptions> markers = new ArrayList<>();

                for (Place place : places) {
                    markers.add(new MarkerViewOptions()
                            .position(place.getLatLng()));
                    mView.showMarkers(markers);
                }
            }

            @Override
            public void onLoadingFailed() {

            }
        });
    }

    @Override
    public MapboxMap.OnMarkerViewClickListener getOnMarkerClick() {
        return (marker, view, adapter) -> {
            loadInstaPosts(marker.getPosition());
            return false;
        };
    }

    private void loadInstaPosts(LatLng position) {
        try {
            GoogleAPILoader.getPlaceByPosition(mView.getActivityContext(), position,
                    new LoadingCallback<Place>() {
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

//                            GoogleAPILoader.getPhoto();
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
    public void onSearch(List<Place> places) {

    }
}