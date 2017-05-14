package com.klimchuk.and.search;

import android.content.Context;

import com.klimchuk.and.data.Place;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public interface SearchContract {

    interface View {

        Context getAppContext();

        void onSearch(List<Place> place);
    }

    interface Presenter {

        void startSearch(String tag);
    }

}
