package com.example.pc_samir.exemplosmatriz.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc_samir.exemplosmatriz.logic.Calculator;
import com.example.pc_samir.exemplosmatriz.view.MatrixColorView;

/**
 * Created by Pc-Samir on 16/08/2016.
 */
public class MatrixColorFragment extends Fragment {

    private MatrixColorView viewMatrix;
    private Calculator calculator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMatrix = new MatrixColorView(inflater.getContext());


        if (calculator != null)
            printOnMatrixView(calculator.getV().getArray());

        return viewMatrix;
    }

    private void printOnMatrixView(double[][] arr) {
        int[][] mtx = new int[arr.length][arr[0].length];
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[i].length; j++) {
                int i1 = 255 - (int)(arr[i][j] * 255/.085);
                mtx[i][j] = Color.argb(i1, 255,0,0);
                Log.d("Color: "+ i + "," + j, Integer.toHexString(mtx[i][j]));
            }
        }
        viewMatrix.setColors(mtx);
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
        if (viewMatrix != null)
            printOnMatrixView(calculator.getV().getArray());
    }
}
