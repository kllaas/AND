package com.klimchuk.and.search_directions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.klimchuk.and.R;
import com.klimchuk.and.activity.MainActivity;
import com.klimchuk.and.domain.Route;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexey on 13.05.17.
 */

public class DirectionsFragment extends Fragment implements DirectionsContract.View {

    public static final int PLACE_PICKER_REQUEST = 1;

    @BindView(R.id.et_from)
    EditText etFrom;
    @BindView(R.id.et_destination)
    EditText etDestination;
    private DirectionsContract.Presenter mPresenter;
    private IDirections.DirectionsCallback mDirectionsCallback;

    public static DirectionsFragment newInstance() {

        Bundle args = new Bundle();

        DirectionsFragment fragment = new DirectionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.et_from)
    public void onFromClick(View v) {

    }

    @OnClick(R.id.et_destination)
    public void onDestClick(View v) {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new DirectionsPresenter(this);

        mDirectionsCallback = ((MainActivity) getActivity());

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

        mDirectionsCallback.onDirectionsSearch(points);
    }
}
