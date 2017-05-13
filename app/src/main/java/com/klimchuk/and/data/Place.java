package com.klimchuk.and.data;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by alexey on 13.05.17.
 */

public class Place {

    private String id;

    private String name;

    private String address;

    private LatLng latLng;

    private String photoReference;

    private int postsCount;

    public Place(String name, String address, LatLng latLng, int postsCount, String photoReference) {
        this.name = name;
        this.address = address;
        this.latLng = latLng;
        this.postsCount = postsCount;
        this.photoReference = photoReference;
    }

    public Place(String id, String name, LatLng latLng) {
        this.id = id;
        this.name = name;
        this.latLng = latLng;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }
}
