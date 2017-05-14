package com.klimchuk.and.data.source;

import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 14.05.17.
 */

public class StaticDataCache {

    public static List<Place> places = new ArrayList<>();

    public static List<Tag> tags = new ArrayList<>();

    public static String[] getTagsStringArray() {
        String[] strings = new String[tags.size()];

        for (int i = 0; i < tags.size(); i++) {
            strings[i] = tags.get(i).getText();
        }
        return strings;
    }
}
