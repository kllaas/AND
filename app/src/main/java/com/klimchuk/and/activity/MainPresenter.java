package com.klimchuk.and.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.maps.IMaps;
import com.klimchuk.and.maps.MapsFragment;
import com.klimchuk.and.search.ISearch;
import com.klimchuk.and.search.SearchFragment;
import com.klimchuk.and.search_directions.DirectionsFragment;
import com.klimchuk.and.search_directions.IDirections;
import com.klimchuk.and.utils.FragmentHelper;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import static com.klimchuk.and.utils.FragmentHelper.DIRECTION_FRAGMENT;
import static com.klimchuk.and.utils.FragmentHelper.MAPS_FRAGMENT;
import static com.klimchuk.and.utils.FragmentHelper.SEARCH_FRAGMENT;

/**
 * Created by alexey on 13.05.17.
 */

public class MainPresenter implements MainContract.Presenter {

    private ISearch.SearchCallback mSearchCallback;

    private BackPressedCallback mBackCallback;

    private ISearch.ClosePlaceCallback mCloseCallback;

    private IMaps.ShowToolbarCallback mToolbarCallback;

    private IDirections.DirectionsCallback mDirectionsCallback;

    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        mView = view;

        showMapsFragment();
        showSearchFragment();
    }

    private void showMapsFragment() {
        FragmentTransaction ft = ((AppCompatActivity) mView.getContext())
                .getSupportFragmentManager().beginTransaction();

        MapsFragment fragment = MapsFragment.newInstance();
        mSearchCallback = fragment;
        mCloseCallback = fragment;
        mDirectionsCallback = fragment;

        FragmentHelper.fragments.add(fragment);

        ft.add(R.id.maps_container, fragment, MAPS_FRAGMENT);
        ft.commit();
    }

    private void showSearchFragment() {
        FragmentTransaction ft = ((AppCompatActivity) mView.getContext())
                .getSupportFragmentManager().beginTransaction();

        SearchFragment fragment = SearchFragment.newInstance();
        mBackCallback = fragment;
        mToolbarCallback = fragment;

        FragmentHelper.fragments.add(fragment);

        ft.add(R.id.search_container, fragment, SEARCH_FRAGMENT);
        ft.commit();
    }

    @Override
    public void onSearch(List<Place> places) {
        mSearchCallback.onSearch(places);
    }

    @Override
    public void onBackPressed() {
        mBackCallback.onBackPressed();
    }

    @Override
    public void onClosePlaceClick() {
        mCloseCallback.onCloseClick();
    }

    @Override
    public void setToolbarVisibility(int visibility) {
        mToolbarCallback.setToolbarVisibility(visibility);
    }

    @Override
    public void showDirectionsFragment() {
        FragmentTransaction ft = ((AppCompatActivity) mView.getContext())
                .getSupportFragmentManager().beginTransaction();

        DirectionsFragment fragment = DirectionsFragment.newInstance();

        FragmentHelper.fragments.add(fragment);

        ft.add(R.id.search_container, fragment, DIRECTION_FRAGMENT).setCustomAnimations(R.anim.slide_down_anim, R.anim.slide_up_anim);
        ft.commit();
    }

    @Override
    public void onDirectionsSearch(LatLng[] points) {
        mDirectionsCallback.onDirectionsSearch(points);
    }


}
