package com.example.pc_samir.exemplosmatriz.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.pc_samir.exemplosmatriz.R;
import com.example.pc_samir.exemplosmatriz.fragment.MainFragment;
import com.example.pc_samir.exemplosmatriz.fragment.MatrixColorFragment;
import com.example.pc_samir.exemplosmatriz.logic.Calculator;

public class MainActivity extends AppCompatActivity {

    private static final int D = 20;

    double[][] vReal = new double[][]{
            {30.5099}, {0.0920}, {0.2015},
            {0.0187},{0.0279}, {0.0250},
            {0.0145}, {0.0175}, {0.0166}};

    private Calculator calculator;

    private void calculateMatrixV() {
        int oT = 3, pT = 3;
        double[] arrayXi = new double[3];
        for (int i = 0; i < arrayXi.length; i++) {
            arrayXi[i] = D * i + D / 2;
        }

        calculator = new Calculator(arrayXi, vReal, oT, pT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateMatrixV();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private MatrixColorFragment matrixColorFragment;
        private MainFragment mainFragment;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    matrixColorFragment = new MatrixColorFragment();
                    matrixColorFragment.setCalculator(calculator);
                    return matrixColorFragment;
                case 1:
                    mainFragment = new MainFragment();
                    mainFragment.setCalculator(calculator);
                    return mainFragment;
                case 2:
                    return new Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Blocks";
                case 1:
                    return "Matrix";
                case 2:
                    return "Blank";
                default:
                    return null;
            }
        }
    }
}