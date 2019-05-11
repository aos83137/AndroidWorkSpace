package com.example.yongseok.findlocation2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    @SuppressLint("MissingPermission")
    private Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    private final LocationListener gpsLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();
            textView.setText("위치정보 : " + provider +'\n'
            + "위도 : " + location + "\n" +
                    "경도 : " + latitude +"\n" +
                    "고도 : " + altitude);

        };

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
