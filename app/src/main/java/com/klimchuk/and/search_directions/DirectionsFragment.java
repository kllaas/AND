package com.klimchuk.and.search_directions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klimchuk.and.R;
import com.klimchuk.and.domain.Route;
import com.klimchuk.and.maps.MapsFragment;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by alexey on 13.05.17.
 */

public class DirectionsFragment extends Fragment implements DirectionsContract.View {

    private DirectionsContract.Presenter mPresenter;

    public static DirectionsFragment newInstance() {

        Bundle args = new Bundle();

        DirectionsFragment fragment = new DirectionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new DirectionsPresenter(this);

        return view;
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void showRoute(Route route) {
        LineString lineString = LineString.fromPolyline(route.getGeometry(), Constants.OSRM_PRECISION_V5);

        List<Position> coordinates = lineString.getCoordinates();
        LatLng[] points = new LatLng[coordinates.size()];

        for (int i = 0; i < coordinates.size(); i++) {
            points[i] = new LatLng(
                    coordinates.get(i).getLatitude(),
                    coordinates.get(i).getLongitude());
        }

        // Draw Points on MapView
//        map.addPolyline(new PolylineOptions()
//                .add(points)
//                .color(Color.parseColor("#009688"))
//                .width(5));
    }
}
