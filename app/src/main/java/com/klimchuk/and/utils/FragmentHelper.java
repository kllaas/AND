package com.klimchuk.and.utils;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 14.05.17.
 */

public class FragmentHelper {

    public static String MAPS_FRAGMENT = "maps";
    public static String SEARCH_FRAGMENT = "search";
    public static String DIRECTION_FRAGMENT = "route";

    public static List<Fragment> fragments = new ArrayList<>();

    public static Fragment getByTag(String tag) {
        for (Fragment fragment : fragments) {
            if (fragment.getTag().equals(tag))
                return fragment;
        }

        return null;
    }

}
