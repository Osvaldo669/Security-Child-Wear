package com.example.securitychild_wear;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.securitychild_wear.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView, latitudText, longitudText;
    private ActivityMainBinding binding;
    private LocationManager locationManager;
    private Button btn_alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
        latitudText = binding.latitudGps;
        longitudText = binding.longitudGps;

        btn_alerta = findViewById(R.id.btn_auxilio);
        btn_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Se mando alerta",Toast.LENGTH_LONG).show();
            }
        });

        locationManager =(LocationManager) getSystemService(LOCATION_SERVICE);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

               // latitudText.setText(("Latitud: "+ String.valueOf((location.getLatitude()))));
                //longitudText.setText(("Longitud: "+ String.valueOf((location.getLongitude()))));
            }
        });
    }

}
