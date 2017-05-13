package com.klimchuk.and.search;

import com.klimchuk.and.data.Place;

import java.util.List;

/**
 * Created by alexey on 13.05.17.
 */

public interface ISearch {

    interface SearchCallback {

        void onSearch(List<Place> searchText);

    }

}
