package com.klimchuk.and.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.search.ISearch;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ISearch.SearchCallback, MainContract.View {

    ISearch.SearchCallback onSearch;

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
}
