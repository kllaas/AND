package com.klimchuk.and.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by alexey on 13.05.17.
 */

public class Place {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
