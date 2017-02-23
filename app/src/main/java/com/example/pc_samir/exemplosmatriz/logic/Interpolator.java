package com.example.pc_samir.exemplosmatriz.logic;

import com.example.pc_samir.exemplosmatriz.model.GravityPoint;

public class Interpolator {


    public static GravityPoint[][] getGravityMatrix(GravityPoint[] points) {
        GravityPoint[][] ret = null;

        if (points == null || points.length < 1) {
            return ret;
        }

        double top = points[0].getLatitude(),
                bottom = points[0].getLatitude(),
                left = points[0].getLongitude(),
                right = points[0].getLongitude();

        for (GravityPoint point : points) {
            if (point.getLatitude() < top) top = point.getLatitude();
            if (point.getLatitude() > bottom) bottom = point.getLatitude();
            if (point.getLongitude() < left) left = point.getLongitude();
            if (point.getLongitude() > right) right = point.getLongitude();
        }

        double dx = bottom - top,
                dy = right - left;

        double k = dx / dy;

        int p = 1, o = 0;

        while (o * p <= points.length) {
            o = (int) (k * ++p);
        }

        ret = new GravityPoint[o+1][p+1];

        for (int i = 0; i <= o; i++) {
            for (int j = 0; j <= p; j++) {
                final double latitude = top + dx * i / o;
                final double longitude = left + dy * j / p;
                final double gravity = getGravityForPoint(latitude, longitude, points);
                ret[i][j] = new GravityPoint(latitude, longitude, 0, gravity);
            }
        }

        return ret;
    }

    private static double getGravityForPoint(double latitude, double longitude, GravityPoint[] points) {
        double gravity = 0;
        double weight = 0;
        for (GravityPoint point : points) {
            final double distance = point.getDistance(latitude, longitude);
            if (distance == 0) return point.getGravity();
            final double distanceInverted = Math.pow(distance, -4);
            weight += distanceInverted;
            gravity += point.getGravity() * distanceInverted;
        }
        return gravity / weight;
    }


}