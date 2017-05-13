package com.klimchuk.and.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;
import com.klimchuk.and.search.ISearch;

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
    public void onSearch(String searchText) {
        mPresenter.onSearch(searchText);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
