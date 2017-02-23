package com.example.pc_samir.exemplosmatriz.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc_samir.exemplosmatriz.R;
import com.example.pc_samir.exemplosmatriz.logic.Calculator;
import com.example.pc_samir.exemplosmatriz.logic.Interpolator;
import com.example.pc_samir.exemplosmatriz.model.GravityPoint;

import Jama.Matrix;

/**
 * Created by Pc-Samir on 16/08/2016.
 */
public class MainFragment extends Fragment {

    private TextView text1;
    private TextView text2;
    private TextView textResult;
    private Calculator calculator;


    private static final GravityPoint[] POINTS = {
            new GravityPoint(0, 0, 0, 1),
            new GravityPoint(0, 1, 0, 2),
            new GravityPoint(0, 2, 0, 3),
            new GravityPoint(1, 0, 0, 1),
            new GravityPoint(1, 1, 0, 2),
            new GravityPoint(1, 2, 0, 3)
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        text1 = (TextView) v.findViewById(R.id.text1);
        text2 = (TextView) v.findViewById(R.id.text2);
        textResult = (TextView) v.findViewById(R.id.text_result);

        GravityPoint[][] result = Interpolator.getGravityMatrix(POINTS);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                text1.append("\n" + result[i][j]);
            }
        }
//        if (calculator != null)
//            printResult();

        return v;
    }

    //Cria Método printMatrix que recebe um Array e imprime
    private void printMatrix(double[][] m, TextView tr) {
        tr.append("\n\n");
        // \t = tab, \n = enter
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                tr.append(String.format("  %.04f\t  ",m[i][j]));
            }
            tr.append("\n");
        }
    }

    //Possibilita que objetos Matrix sejam convertidos em Array e chama o Método printMatrix
    private void printMatrix(Matrix m, TextView tr) {
        printMatrix(m.getArray(), tr);
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;

        if (text1 != null) {
            printResult();
        }
    }

    private void printResult() {
        Matrix v = calculator.getV();
        text1.setText("Observado");
        text2.setText("Calculado");
        printMatrix(calculator.getVReal(), text1);
        printMatrix(v, text2);
        printMatrix(calculator.getA().times(v), text1);
        printMatrix(calculator.getG(), text2);
    }
}
