package com.example.pc_samir.exemplosmatriz.logic;

import com.example.pc_samir.exemplosmatriz.model.GravityPoint;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by hemobile on 21/02/17.
 */
public class InterpolatorTest {

    private static final GravityPoint[] POINTS = {
            new GravityPoint(0, 0, 0, 1),
            new GravityPoint(1, 2, 0, 1),
            new GravityPoint(0, 2, 0, 1),
            new GravityPoint(1, 0, 0, 1)
    };

    @Test
    public void getGravityMatrix() throws Exception {
        GravityPoint[][] result = Interpolator.getGravityMatrix(POINTS);
        Assert.assertEquals(3, result.length);
        Assert.assertEquals(5, result[0].length);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(result[i][j]);
            }
        }

    }

}