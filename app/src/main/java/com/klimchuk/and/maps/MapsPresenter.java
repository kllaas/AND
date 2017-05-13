package com.klimchuk.and.maps;

import static com.klimchuk.and.maps.MapsContract.Presenter;
import static com.klimchuk.and.maps.MapsContract.View;

/**
 * Created by alexey on 13.05.17.
 */

public class MapsPresenter implements Presenter {

    private View mView;

    MapsPresenter(View view) {
        mView = view;
    }

    @Override
    public void onSearch() {

    }
}
