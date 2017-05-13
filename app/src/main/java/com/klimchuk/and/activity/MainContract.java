package com.klimchuk.and.activity;

import android.content.Context;

/**
 * Created by alexey on 13.05.17.
 */

public interface MainContract  {

    interface View {

        Context getContext();

    }

    interface Presenter {

        void onSearch(String searchText);
    }

}
