package com.example.yongseok.finelocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LocationManager manager;
    private TextView textView;
//    private LocationListener listener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) { //위도,경도가 이동 되었을 떄 불러오는 콜백임 보통 이친구만 건드리면 된다고 하심.
//            textView = findViewById(R.id.textView);
//            String result = "" +location.getLatitude();
//            result += "," + location.getLongitude();
//            textView.setText(result);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) { // 지금 이 좌표를 GPS가 주는지 네트워크가 주는지 그것을 확인 할 수 있음
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) { // 지금 이 좌표를 GPS가 주는지 네트워크가 주는지 그것을 확인 할 수 있음
//
//        }
//    };
//
//
//    private void initLocationManger() {
//        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            manager.requestLocationUpdates( // PERMISSION 체크를 해야되서 if문 열어서 해준거임
//                    LocationManager.GPS_PROVIDER,
//                    5000, 10,
//                    listener
//            );
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
//        if (Build.VERSION.SDK_INT >= 23) {
//            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION); //권한 확인
//            if (permission != PackageManager.PERMISSION_GRANTED) { //권한 없으ㅡ면 가져오는 거임
//                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
//                return;
//            } else { // permission 있으면 바로 init메서드 호출하면 됨
//                initLocationManger();
//            }
//        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 2) {
//            if(permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                initLocationManger();
//            } else {
//                Toast.makeText(this, "osoi", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            Toast.makeText(this, "test안됨.", Toast.LENGTH_SHORT).show();
//        }
//    }
}

