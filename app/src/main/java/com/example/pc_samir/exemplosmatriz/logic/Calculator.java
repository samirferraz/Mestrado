package com.example.pc_samir.exemplosmatriz.logic;

import android.util.Log;

import Jama.Matrix;

/**
 * Created by Pc-Samir on 04/07/2016.
 */
public class Calculator {

    final double D = 20;
    final double H = 20;
    final double GAMA = 1;
    final double e = 0.01;

    private final double[] arrayXi;
    private final Matrix VReal;
    private Matrix G;
    private final int oT;
    private final int pT;

    private double lo = 0.1;
    private static final double TOL = 0.00002;

    private final Matrix A;
    private Matrix V;

    public Calculator(double[] arrayXi, double[][] vReal, int oT, int pT) {
        this.arrayXi = arrayXi;
        this.oT = oT;
        this.pT = pT;
        A = calculateMatrixA();
        this.VReal = new Matrix(vReal);
        this.G = A.times(VReal);
        calculateV();
    }

    private Matrix calculateMatrixA() {
        double[][] arrayA = new double[arrayXi.length][oT*pT];
        for (int i = 0; i < arrayXi.length; i++) {
            for (int o = 0; o < oT; o++) {
                for (int p = 0; p < pT; p++) {
                    int j = o * arrayXi.length + p;
                    double k1 = o * H;
                    double k2 = (o + 1) * H;
                    double k3 = arrayXi[i] - p * D;
                    double k4 = arrayXi[i] - (p + 1) * D;
                    double r1 = Math.pow(k1 * k1 + k3 * k3, 0.5);
                    double r2 = Math.pow(k2 * k2 + k3 * k3, 0.5);
                    double r3 = Math.pow(k1 * k1 + k4 * k4, 0.5);
                    double r4 = Math.pow(k2 * k2 + k4 * k4, 0.5);
                    double t1 = Math.atan(k3 / k1);
                    double t2 = Math.atan(k3 / k2);
                    double t3 = Math.atan(k4 / k1);
                    double t4 = Math.atan(k4 / k2);
                    arrayA[i][j] = 2 * GAMA * (k3 * Math.log((r2 * r3) / (r1 * r4)) + D * Math.log(r4 / r3) - k2 * (t4 - t2) + k1 * (t3 - t1));
                }
            }
        }
        return new Matrix(arrayA);
    }

    private void calculateV() {
        Matrix VAux;
        Matrix WV = Matrix.identity(oT*pT,oT*pT);
        boolean canContinue = true;
        int count = 0;
        while (canContinue) {
            VAux = V;
            Matrix WE = A.times(WV.times(A.transpose())).times(lo*lo);
            V = WV.times(A.transpose()).times(A.times(WV.times(A.transpose())).plus(WE).inverse().times(G));

            canContinue = VAux == null || V.minus(VAux).normF() > TOL;
            Log.d("Calculator", count++ + ": " + matrixToString(V));
            if (canContinue) {
                for (int i = 0; i < WV.getRowDimension(); i++) {
                    double vii = V.get(i, 0);
                    WV.set(i, i, vii * vii + e);
                }
            }
        }
    }

    private String matrixToString(Matrix m) {
        StringBuffer tr = new StringBuffer();
        // \t = tab, \n = enter
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                tr.append(String.format("  %.4f\t  ",m.get(i,j)));
            }
            tr.append("\n");
        }
        return tr.toString();
    }

    public Matrix getA() {
        return A;
    }

    public Matrix getV() {
        return V;
    }

    public Matrix getG() {
        return G;
    }

    public Matrix getVReal() {
        return VReal;
    }
}