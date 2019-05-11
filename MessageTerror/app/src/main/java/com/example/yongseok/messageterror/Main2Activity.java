package com.example.yongseok.messageterror;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText editText, editData;
    private Button sendButton,x10Button;
    private String phoneNumber, messageData;
    private static final int REQ_SEND_SMS = 1;

    private void sendSMS(String phoneNumber, String data) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, data, null, null);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setStringToEdit();
            if (Build.VERSION.SDK_INT >= 23) {
                int permissioin = ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.SEND_SMS);
                if (permissioin != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQ_SEND_SMS);
                    return;
                }
            }
            sendSMS(phoneNumber, messageData);
            Toast.makeText(Main2Activity.this, "문자 보내기 성공", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQ_SEND_SMS) return;
        setStringToEdit();
        if (permissions[0].equals(Manifest.permission.SEND_SMS) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSMS(phoneNumber, messageData);
        } else {
            Toast.makeText(this, "문자 전송 권한이 없습니다.!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.EditTextPhone);
        editData = findViewById(R.id.EditTextData);
        sendButton = findViewById(R.id.sendButton);
        x10Button = findViewById(R.id.buttonFor);

        sendButton.setOnClickListener(listener);
        x10Button.setOnClickListener(v->{
            setStringToEdit();
            if (Build.VERSION.SDK_INT >= 23) {
                int permissioin = ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.SEND_SMS);
                if (permissioin != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQ_SEND_SMS);
                    return;
                }
            }
            for(int i=0;i<1001;i++)
                if(i%100 == 0)
                    sendSMS(phoneNumber, messageData);
            Toast.makeText(this, "문자 보내기 성공", Toast.LENGTH_SHORT).show();
        });


    }

    public void setStringToEdit() {
        phoneNumber = editText.getText().toString().trim();
        messageData = editData.getText().toString().trim();
        if (phoneNumber.length() == 0) return;
    }
}
