package com.example.samirferraz.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc_samir.exemplosmatriz.R;
import com.example.pc_samir.exemplosmatriz.model.GravityPoint;
import com.example.samirferraz.myapplication.database.AltitudeDatabaseHelper;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AltitudeActivityFragment extends Fragment {

    public static final float INNER_RADIUS = 0.5f;
    public static final float MEDIUM_RADIUS = 1f;
    public static final float OUTTER_RADIUS = 1.5f;
    private AltitudeDatabaseHelper databaseHelper;

    TextView textView;

    public AltitudeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_altitude, container, false);
        inflate.findViewById(R.id.bt_print_locations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printLocations(view);
            }
        });
        textView = (TextView) inflate.findViewById(R.id.textResult);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new AltitudeDatabaseHelper(getActivity());
        calculateMatrix();
    }

    private void calculateMatrix() {
        float lat = -12.9f;
        float lng = -38.4f;
        GravityPoint centralPoint = new GravityPoint(lat, lng);
        double[][] calculatedAltitudes = new double[8][3];
        double[][] altitudesQuantity = new double[8][3];

        final List<GravityPoint> points = databaseHelper.getAllPoints();
        for (GravityPoint gravityPoint : points) {
            final double angle = gravityPoint.getAngle(centralPoint);
            int index = (int) (angle > 0 ? angle / 45 : 7 + (int)(angle / 45));
            if (centralPoint.getGradDistance(gravityPoint) < INNER_RADIUS) {
                calculatedAltitudes[index][0] += gravityPoint.getAltitude();
                altitudesQuantity[index][0]++;
            } else if (centralPoint.getGradDistance(gravityPoint) < MEDIUM_RADIUS) {
                calculatedAltitudes[index][1] += gravityPoint.getAltitude();
                altitudesQuantity[index][1]++;
            } else if (centralPoint.getGradDistance(gravityPoint) < OUTTER_RADIUS) {
                calculatedAltitudes[index][2] += gravityPoint.getAltitude();
                altitudesQuantity[index][2]++;
            }
        }
        printMatrix(altitudesQuantity, textView);

        for (int i = 0; i < calculatedAltitudes.length; i++) {
            for (int j = 0; j < calculatedAltitudes[i].length; j++) {
                if (altitudesQuantity[i][j] > 0)
                    calculatedAltitudes[i][j] /= altitudesQuantity[i][j];
            }
        }

        printMatrix(calculatedAltitudes, textView);
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

    public void printLocations(View v) {
        float lat = -12.9f;
        float lng = -38.4f;
        float altitude = databaseHelper.getAltitude(lat, lng);


        textView.setText(String.format("Latitude: %2$.6f Long: %3$.6f Altitude: %1$.3f ", altitude, lat, lng));
        textView.append("\n");
        textView.append(databaseHelper.getPrintableAltitudePoints(lat, lng));
    }
}