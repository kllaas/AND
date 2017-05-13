package com.klimchuk.and.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.maps.MapsFragment;
import com.klimchuk.and.search.ISearch;
import com.klimchuk.and.search.SearchFragment;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public class MainPresenter implements MainContract.Presenter {

    private ISearch.SearchCallback mSearchCallback;

    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        mView = view;

        showMapsFragment();
        showSearchFragment();


    }

    private void showMapsFragment() {
        FragmentTransaction ft = ((AppCompatActivity)mView.getContext())
                .getSupportFragmentManager().beginTransaction();

        MapsFragment fragment = MapsFragment.newInstance();
        mSearchCallback = fragment;

        ft.add(R.id.maps_container, fragment);
        ft.commit();
    }

    private void showSearchFragment() {
        FragmentTransaction ft = ((AppCompatActivity)mView.getContext())
                .getSupportFragmentManager().beginTransaction();

        SearchFragment fragment = SearchFragment.newInstance();

        ft.add(R.id.search_container, fragment);
        ft.commit();
    }

    @Override
    public void onSearch(List<Place> places) {
        mSearchCallback.onSearch(places);
    }
}
