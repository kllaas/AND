package com.klimchuk.and.search_directions;

import android.content.Context;

/**
 * Created by alexey on 13.05.17.
 */

public interface DirectionsContract {

    interface View {

        Context getAppContext();

    }

    interface Presenter {

        void startSearch(String tag);
    }

}
