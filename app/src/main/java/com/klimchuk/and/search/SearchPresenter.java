package com.klimchuk.and.search;

import com.klimchuk.and.maps.MapsContract;

/**
 * Created by alexey on 13.05.17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private MapsContract.View mView;

    SearchPresenter(MapsContract.View view) {
        mView = view;
    }

}
