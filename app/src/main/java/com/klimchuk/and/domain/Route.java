package com.klimchuk.and.domain;

import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("geometry")
    private String geometry;

    @SerializedName("distance")
    private double distance;

    @SerializedName("waypoints")
    private String waypoints;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }
}
