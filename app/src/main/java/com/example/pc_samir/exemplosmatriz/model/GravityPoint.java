package com.example.pc_samir.exemplosmatriz.model;

import android.location.Location;

/**
 * Created by Leaudro on 21/02/17.
 */

public class GravityPoint {

    private double latitude;
    private double longitude;
    private double altitude;
    private double gravity;

    public GravityPoint() {

    }

    public GravityPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GravityPoint(double latitude, double longitude, double altitude) {
        this(latitude, longitude);
        this.altitude = altitude;
    }

    public GravityPoint(double latitude, double longitude, double altitude, double gravity) {
        this(latitude, longitude, altitude);
        this.gravity = gravity;
    }

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

    public double getGradDistance(GravityPoint anotherPoint) {
        return Math.sqrt(Math.pow(latitude - anotherPoint.latitude, 2) + Math.pow(longitude - anotherPoint.longitude, 2));
    }

    public double getAngle(GravityPoint anotherPoint) {
        return Math.atan2(latitude - anotherPoint.latitude, longitude - anotherPoint.longitude) * 180 / Math.PI;
    }

    @Override
    public String toString() {
        return String.format("Latitude: %.6f, Longitude: %.6f, Gravidade: %.3f", latitude, longitude, gravity);
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
