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

import Jama.Matrix;

/**
 * Created by Pc-Samir on 16/08/2016.
 */
public class MainFragment extends Fragment {

    private TextView text1;
    private TextView text2;
    private TextView textResult;
    private Calculator calculator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        text1 = (TextView) v.findViewById(R.id.text1);
        text2 = (TextView) v.findViewById(R.id.text2);
        textResult = (TextView) v.findViewById(R.id.text_result);

        if (calculator != null)
            printResult();

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
