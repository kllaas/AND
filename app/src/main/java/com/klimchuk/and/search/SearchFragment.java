package com.klimchuk.and.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klimchuk.and.maps.MapsFragment;
import com.klimchuk.and.R;
import com.klimchuk.and.search.ISearch.SearchCallback;

import butterknife.ButterKnife;

/**
 * Created by alexey on 13.05.17.
 */

public class SearchFragment extends Fragment implements SearchContract.View{

    private SearchCallback searchCallback;

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

        return view;
    }
}
