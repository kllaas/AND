package com.klimchuk.and.activity;

import android.content.Context;

import com.klimchuk.and.data.Place;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public interface MainContract  {

    interface View {

        Context getContext();

    }

    interface Presenter {

        void onSearch(List<Place> places);

        void onBackPressed();
    }

}
