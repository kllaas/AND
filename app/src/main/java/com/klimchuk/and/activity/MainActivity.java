package com.klimchuk.and.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.maps.IMaps;
import com.klimchuk.and.search.ISearch;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ISearch.SearchCallback, MainContract.View, ISearch.ClosePlaceCallback, IMaps.ShowToolbarCallback, ISearch.ShowDirectionsFragment {

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
}
