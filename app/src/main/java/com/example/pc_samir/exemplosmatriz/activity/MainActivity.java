package com.example.pc_samir.exemplosmatriz.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.pc_samir.exemplosmatriz.R;
import com.example.pc_samir.exemplosmatriz.logic.Calculator;
import com.example.pc_samir.exemplosmatriz.view.MatrixColorView;

import Jama.Matrix;

public class MainActivity extends AppCompatActivity {

    private static final int D = 20;
    private TextView text1;
    private TextView text2;
    private TextView textResult;
    private MatrixColorView viewMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        textResult = (TextView) findViewById(R.id.text_result);
        viewMatrix = (MatrixColorView) findViewById(R.id.view_matrix);
        matrizV();
        invert();
    }

    private void matrizV() {
        double[] arrayXi = new double[3];
        double[] g = new double[3];
        for (int i = 0; i < arrayXi.length; i++) {
            arrayXi[i] = D *i + D /2;
            g[i] = 10;
        }
        g[1] = 20;

        Calculator calc = new Calculator(arrayXi, g, 3, 3);
        Matrix V = calc.getV();

        printOnMatrixView(V.getArray());

        printMatrix(V, text1);
        printMatrix(calc.getG(), text2);
        printMatrix(calc.getA().times(V), textResult);
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

    private void invert(){

        int c = 0;
        int k = 0;
        while (k < 10){
            k = k + 1;
        }

    }

    private void printResult() {

    // teste--------------------------------------------------------------------
        double[][] arrayM1 = new double[2][2];
        double[][] arrayM2 = new double[2][2];
        double[][] arrayM3 = new double[2][2];
        for (int i = 0; i < arrayM1.length; i++) {
            for (int j = 0; j < arrayM1[i].length; j++) {
                arrayM1[i][j] = 1;
                arrayM2[i][j] = 2;
                arrayM3[i][j] = 3;
            }
        }

        arrayM1 [1][1] = 2;//Math.atan(0.1);
        arrayM3 [0][0] = 1;
        arrayM3 [0][1] = 2;
        arrayM3 [1][0] = 3;
        arrayM3 [1][1] = 4;
        double l = 2;

        Matrix m1 = new Matrix (arrayM1);
        Matrix m2 = new Matrix (arrayM2);
        Matrix m3 = new Matrix (arrayM3);
        //m3 = (Matrix.identity(2,2)).times(50);

    //Chama Método printMatrix ------------------------------------------------------------------------

        //printMatrix(m1, text1); // igual a printMatrix(arrayM1, text1);
        //printMatrix(m2, text2); // printMatrix(m2, text2);
        //printMatrix(m1.times(m2.times(m3.transpose())).times(Math.pow(l,2)), textResult);//times(m2), textResult);

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
}