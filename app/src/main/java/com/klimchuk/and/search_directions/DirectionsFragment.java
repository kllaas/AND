package com.klimchuk.and.search_directions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klimchuk.and.R;

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
}
