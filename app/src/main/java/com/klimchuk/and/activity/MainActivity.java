package com.klimchuk.and.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.klimchuk.and.R;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.maps.IMaps;
import com.klimchuk.and.search.ISearch;
import com.klimchuk.and.search_directions.IDirections;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import static com.klimchuk.and.search_directions.DirectionsFragment.PLACE_PICKER_REQUEST;

public class MainActivity extends AppCompatActivity implements ISearch.SearchCallback,
        MainContract.View, ISearch.ClosePlaceCallback,
        IMaps.ShowToolbarCallback, ISearch.ShowDirectionsFragment, IDirections.DirectionsCallback {

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
    }

    @Override
    public void onSearch(List<Place> places) {
        mPresenter.onSearch(places);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onBackPressed();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                com.google.android.gms.location.places.Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onCloseClick() {
        mPresenter.onClosePlaceClick();
    }

    @Override
    public void setToolbarVisibility(int visibility) {
        mPresenter.setToolbarVisibility(visibility);
    }

    @Override
    public void onDirectionsClick() {
        mPresenter.showDirectionsFragment();
    }

    @Override
    public void onDirectionsSearch(LatLng[] points) {
        mPresenter.onDirectionsSearch(points);
    }
}
