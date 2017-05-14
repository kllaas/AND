package com.klimchuk.and.maps;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klimchuk.and.R;
import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.search.ISearch;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsFragment extends Fragment implements MapsContract.View, ISearch.SearchCallback {

    @BindView(R.id.map_view)
    MapView mapView;

    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mSlidingLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MapboxMap mMap;

    private MapsContract.Presenter mPresenter;

    public static MapsFragment newInstance() {

        Bundle args = new Bundle();

        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps_fragment, container, false);

        configureViews(savedInstanceState, view);

        return view;
    }

    private void configureViews(Bundle savedInstanceState, View view) {
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);

        mSlidingLayout.setAnchorPoint(0.5f);

        mapView.getMapAsync(mapboxMap -> {

            mMap = mapboxMap;

            mPresenter = new MapsPresenter(this);
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public void showMarkers(List<MarkerViewOptions> markers) {

        for (MarkerViewOptions marker : markers) {

            mMap.addMarker(marker);
        }

        mMap.getMarkerViewManager().setOnMarkerViewClickListener(mPresenter.getOnMarkerClick());
    }

    @Override
    public void moveToBounds(List<Marker> p) {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < p.size(); i++) {
            builder.include(p.get(i).getPosition());
        }

        LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the mMap in pixels

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void showPlace(RecyclerAdapter recyclerAdapter) {
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public List<Marker> getMarkers() {
        return mMap.getMarkers();
    }

    @Override
    public void clearMarkers() {
        mMap.clear();
    }

    @Override
    public void onSearch(List<Place> places) {
        mPresenter.onSearch(places);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
