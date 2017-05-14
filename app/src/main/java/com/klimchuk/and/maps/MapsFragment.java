package com.klimchuk.and.maps;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.klimchuk.and.R;
import com.klimchuk.and.activity.MainActivity;
import com.klimchuk.and.adapter.RecyclerAdapter;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.source.StaticDataCache;
import com.klimchuk.and.search.ISearch;
import com.klimchuk.and.search_directions.DirectionsFragment;
import com.klimchuk.and.search_directions.IDirections;
import com.klimchuk.and.utils.FragmentHelper;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsFragment extends Fragment implements MapsContract.View, ISearch.SearchCallback, ISearch.ClosePlaceCallback, IDirections.DirectionsCallback {

    private static final int VERTICAL_ITEM_SPACE = 48;
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mSlidingLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.drag_view)
    View dragView;
    @BindView(R.id.ic_pick_location)
    ImageView locationPicker;

    @BindView(R.id.btn_choose_place)
    Button btnChoosePlace;

    private IMaps.ShowToolbarCallback mShowToolbarCallback;

    private MapboxMap mMap;

    private RecyclerView.LayoutManager mLayoutManager;

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

        mShowToolbarCallback = ((MainActivity) getActivity());

        return view;
    }

    private void configureViews(Bundle savedInstanceState, View view) {
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);

        mSlidingLayout.setAnchorPoint(0.5f);
        mSlidingLayout.setPanelHeight(0);

        mapView.setStyleUrl("mapbox://styles/piekie/cj2kpnv9b002h2srs7d45c8nv");
        mapView.getMapAsync(mapboxMap -> {

            mMap = mapboxMap;

            mMap.setOnCameraChangeListener(pos -> {
                if (pos.zoom > 15) {
                    if (mapboxMap.getMarkers().size() == 0)
                        mPresenter.configureMarkers(StaticDataCache.places, false);
                } else
                    mMap.clear();
            });
            mPresenter = new MapsPresenter(this);
        });

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSlidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                switch (newState) {
                    case EXPANDED:
                        mShowToolbarCallback.setToolbarVisibility(View.VISIBLE);
                        break;
                    case ANCHORED:
                        mShowToolbarCallback.setToolbarVisibility(View.GONE);
                        break;
                    case COLLAPSED:
                        mShowToolbarCallback.setToolbarVisibility(View.GONE);
                        break;
                }
            }

        });
    }


    @Override
    public void showMarkers(List<MarkerViewOptions> markers) {

        for (MarkerViewOptions marker : markers) {

            mMap.addMarker(marker);
        }

        mMap.getMarkerViewManager().setOnMarkerViewClickListener(mPresenter.getOnMarkerClick());
    }

    @Override
    public void moveToBounds(List<Marker> list) {

        try {
            if (list.size() == 0)
                return;

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include(list.get(i).getPosition());
            }

            LatLngBounds bounds = builder.build();
            int padding = 0; // offset from edges of the mMap in pixels

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);

        } catch (Exception e) {

            if (list.size() != 0) {
                CameraPosition position = new CameraPosition.Builder()
                        .target(list.get(0).getPosition()) // Sets the new camera position
                        .tilt(30) // Set the camera tilt
                        .build();

                mMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 7000);
            }
        }
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
    public void setSlidingViewVisibility(boolean visibility) {
        mSlidingLayout.setPanelHeight((int) getResources().getDimension(R.dimen.panel_height));
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

    @OnClick(R.id.btn_choose_place)
    public void choosePlace() {
        ((DirectionsFragment) FragmentHelper.getByTag(FragmentHelper.DIRECTION_FRAGMENT)).chooseLocation();
    }

    @Override
    public void onCloseClick() {
        mRecyclerView.smoothScrollToPosition(0);
        mSlidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @Override
    public void onDirectionsSearch(LatLng[] points) {
        Thread th = new Thread(() -> mMap.addPolyline(new PolylineOptions()
                .add(points)
                .color(Color.parseColor("#4cd93f"))
                .width(500)));
        th.start();
    }

    public void searchLocation() {
        locationPicker.setVisibility(View.VISIBLE);
        btnChoosePlace.setVisibility(View.VISIBLE);
    }

    public void removePickerVisibility() {
        locationPicker.setVisibility(View.GONE);
        btnChoosePlace.setVisibility(View.GONE);
    }

    public LatLng getPosition() {
        return mMap.getCameraPosition().target;
    }
}
