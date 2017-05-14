package com.klimchuk.and.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.klimchuk.and.R;

import java.util.ArrayList;

/**
 * Created by alexey on 14.05.17.
 */

public class FragmentHelper {

    public static String MAPS_FRAGMENT = "maps";
    public static String SEARCH_FRAGMENT = "search";
    public static String DIRECTION_FRAGMENT = "route";

    public static ArrayList<Fragment> fragments = new ArrayList<>();

    public static Fragment getByTag(String tag) {
        for (Fragment fragment : fragments) {
            if (fragment.getTag().equals(tag))
                return fragment;
        }

        return null;
    }

    public static void removeFragment(AppCompatActivity activity, String directionFragment) {
        Fragment fragment = getByTag(directionFragment);

        (activity.getSupportFragmentManager())
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_down_anim, R.anim.slide_up_anim,
                        R.anim.slide_down_anim, R.anim.slide_up_anim)
                .remove(fragment)
                .commit();

        fragments.remove(fragment);
    }
}
