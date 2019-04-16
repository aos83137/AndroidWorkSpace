package com.example.yongseok.permission;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_SEND_SMS = 1;
    private ToggleButton toggleButton;
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Receiver", "onReceive");
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1); // 브ㅡㄹ루투스 상태를 EXTRA_STATE 담아서 날라온다
            toggleButton = findViewById(R.id.toggleButton);

            switch(state){
                //TURNING_ON도 있다 켜는도중임
                case BluetoothAdapter.STATE_ON:
                    toggleButton.setChecked(true);
                    Toast.makeText(context, "Bluetooth ON", Toast.LENGTH_SHORT).show();
//                    Log.d("Receiver", "Bluetooth ON");
                    break;
                case BluetoothAdapter.STATE_OFF:
                    toggleButton.setChecked(false);
                    Toast.makeText(context, "Bluetooth OFF", Toast.LENGTH_SHORT).show();
//                    Log.d("Receiver", "Bluetooth OFF");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = findViewById(R.id.toggleButton);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!bluetoothAdapter.isEnabled()){
            toggleButton.setChecked(false);
        }else{
            toggleButton.setChecked(true);
        }

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // 이게 빠지면 폰에 있는 웹브라우저를 호출하게 됨 이걸해야 내창에서뜸
        webView.loadUrl("https://www.naver.com/");

    }

    private void sendSMS(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("010-7225-9161", null, "nemuii", null,null);
    }

    public void onSendSMS(View v){ //권한 확인하는 리스너가 들어간 함수
        if( Build.VERSION.SDK_INT >= 23){
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS); //권한 확인
            if(permission != PackageManager.PERMISSION_GRANTED){ //권한 없으ㅡ면 가져오는 거임
                requestPermissions(new String[] {Manifest.permission.SEND_SMS}, REQ_SEND_SMS);
                return;
            }
        }
        sendSMS();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode != REQ_SEND_SMS) return;
        if(permissions[0].equals(Manifest.permission.SEND_SMS)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            sendSMS();
        else
            Toast.makeText(this, "문자 전송 권한이 없습니다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart(){
        super.onStart();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onStop(){ // 해제도 해야함!!!
        super.onStop();
        unregisterReceiver(receiver);
    }

    public void onBlueState(View view) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        toggleButton = findViewById(R.id.toggleButton);
        if(bluetoothAdapter == null){
            Toast.makeText(this, "블루투스 없다리", Toast.LENGTH_SHORT).show();
        }else{
            if(!bluetoothAdapter.isEnabled()){
                bluetoothAdapter.enable();
                toggleButton.setChecked(true);
                Toast.makeText(this, "블루투스 온!", Toast.LENGTH_SHORT).show();
            }else{
                bluetoothAdapter.disable();
                toggleButton.setChecked(false);
                Toast.makeText(this, "블루투스 오프!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
