package com.klimchuk.and.search_directions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.klimchuk.and.R;
import com.klimchuk.and.activity.MainActivity;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.google.GoogleAPILoader;
import com.klimchuk.and.domain.Route;
import com.klimchuk.and.maps.MapsFragment;
import com.klimchuk.and.utils.FragmentHelper;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.services.Constants;
import com.mapbox.services.commons.geojson.LineString;
import com.mapbox.services.commons.models.Position;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexey on 13.05.17.
 */

public class DirectionsFragment extends Fragment implements DirectionsContract.View {

    @BindView(R.id.et_from)
    EditText etFrom;

    @BindView(R.id.et_destination)
    EditText etDestination;

    @BindView(R.id.btn_back)
    ImageButton back;

    private DirectionsContract.Presenter mPresenter;

    private IDirections.DirectionsCallback mDirectionsCallback;

    private LatLng[] searchLocations = new LatLng[2];
    private boolean currentLocationChoose;

    public static DirectionsFragment newInstance() {

        Bundle args = new Bundle();

        DirectionsFragment fragment = new DirectionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.et_from)
    public void onFromClick(View v) {
        currentLocationChoose = true;
        etFrom.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLow));
        etDestination.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        ((MapsFragment) FragmentHelper.getByTag(FragmentHelper.MAPS_FRAGMENT)).searchLocation();
    }

    @OnClick(R.id.et_destination)
    public void onDestClick(View v) {
        currentLocationChoose = false;
        etDestination.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLow));
        etFrom.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        ((MapsFragment) FragmentHelper.getByTag(FragmentHelper.MAPS_FRAGMENT)).searchLocation();
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick(View v) {
        if (searchLocations[0] != null & searchLocations[1] != null)
            mPresenter.startSearch(String.valueOf(searchLocations[0].getLatitude()), String.valueOf(searchLocations[0].getLongitude()),
                    String.valueOf(searchLocations[1].getLatitude()), String.valueOf(searchLocations[1].getLongitude()));
    }

    @OnClick(R.id.btn_back)
    public void onCancel(View v) {
        ((MapsFragment) FragmentHelper.getByTag(FragmentHelper.MAPS_FRAGMENT)).removePickerVisibility();
        FragmentHelper.removeFragment((AppCompatActivity) getActivity(), FragmentHelper.DIRECTION_FRAGMENT);
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

    public void chooseLocation() {
        try {
            GoogleAPILoader.getPlaceByPosition(getContext(),
                    ((MapsFragment) FragmentHelper.getByTag(FragmentHelper.MAPS_FRAGMENT)).getPosition(),
                    new LoadingCallback<Place>() {
                        @Override
                        public void onPlaceLoaded(Place place) {

                            if (currentLocationChoose) {
                                searchLocations[0] = place.getLatLng();
                                etFrom.setText(place.getAddress());
                            } else {
                                searchLocations[1] = place.getLatLng();
                                etDestination.setText(place.getAddress());
                            }
                        }

                        @Override
                        public void onLoadingFailed() {

                        }
                    });

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
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
