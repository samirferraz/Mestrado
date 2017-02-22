package com.example.pc_samir.exemplosmatriz.model;

import android.location.Location;

/**
 * Created by Leaudro on 21/02/17.
 */

public class GravityPoint {

    public GravityPoint() {

    }

    public GravityPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GravityPoint(double latitude, double longitude, double gravity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.gravity = gravity;
    }

    private double latitude;
    private double longitude;
    private double gravity;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getDistance(double latitude, double longitude) {
        float[] result = new float[3];
        Location.distanceBetween(this.latitude, this.longitude, latitude, longitude, result);
        return result[0];
    }

    public double getDistance(GravityPoint anotherPoint) {
        return getDistance(anotherPoint.latitude, anotherPoint.longitude);
    }

    @Override
    public String toString() {
        return String.format("Latitude: %s, Longitude: %s, Gravidade: %s", latitude, longitude, gravity);
    }
}
