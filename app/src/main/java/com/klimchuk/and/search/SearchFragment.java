package com.klimchuk.and.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.klimchuk.and.R;
import com.klimchuk.and.activity.MainActivity;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.search.ISearch.SearchCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexey on 13.05.17.
 */

public class SearchFragment extends Fragment implements SearchContract.View{

    @BindView(R.id.et_search)
    AutoCompleteTextView editText;
    private SearchCallback searchCallback;
    private SearchContract.Presenter mPresenter;

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        ButterKnife.bind(this, view);

        searchCallback = ((MainActivity) getActivity());

        mPresenter = new SearchPresenter(this);

        return view;
    }

    @OnClick(R.id.btn_search)
    public void onClick(View v) {
        mPresenter.startSearch(editText.getText().toString());
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void onSearch(List<Place> place) {
        searchCallback.onSearch(place);
    }
}
