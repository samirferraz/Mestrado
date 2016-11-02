package com.example.pc_samir.exemplosmatriz.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_samir.exemplosmatriz.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Pc-Samir on 16/08/2016.
 */
public class LocationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
                                                        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    private TextView textLocation;
    private Button button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        textLocation = (TextView) v.findViewById(R.id.text_location);
        button = (Button) v.findViewById(R.id.bt_location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGoogleApiClient.isConnected()) {
                    button.setText("Iniciar Localização");
                    mGoogleApiClient.disconnect();
                } else {
                    button.setText("Parar");
                    mGoogleApiClient.connect();
                }
            }
        });
        return v;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        final FragmentActivity activity = getActivity();
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        textLocation.setText("Localizando dados de GPS...");

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, getLocationRequest(), new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null && location.getAltitude() > 0.000f) {
                    StringBuilder strLocation = new StringBuilder();
                    strLocation.append("\nLatitude: ").append(location.getLatitude());
                    strLocation.append("\nLongitude: ").append(location.getLongitude());
                    strLocation.append("\nAltitude: ").append(location.getAltitude());
                    strLocation.append("\nAccuracy: ").append(location.getAccuracy());
                    strLocation.append("\nProvider: ").append(location.getProvider());

                    textLocation.setText(strLocation.toString());
                }
            }
        });
    }

    private LocationRequest getLocationRequest() {
        return LocationRequest
                .create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(6000)
                .setFastestInterval(1000);
    }

    @Override
    public void onConnectionSuspended(int i) {
        button.setText("Iniciar Localização");
        Toast.makeText(getActivity(), "Conexão com GPS suspensa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        button.setText("Iniciar Localização");
        Toast.makeText(getActivity(), "Falha no GPS", Toast.LENGTH_SHORT).show();
    }
}
