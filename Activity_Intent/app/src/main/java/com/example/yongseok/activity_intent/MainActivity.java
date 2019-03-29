package com.example.yongseok.activity_intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQ_THIRD =1211;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonSecond).setOnClickListener(this);
        findViewById(R.id.buttonThird).setOnClickListener(this);
        findViewById(R.id.buttonSms).setOnClickListener(v -> {
            Intent i= new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("smsto:010-7225-9161"));
            i.putExtra("sms_body", "Hello");
            startActivity(Intent.createChooser(i, "Select one"));
        });
        findViewById(R.id.buttonPhoto).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Main", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main","onResume");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSecond){
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("message", "Hello!!!");
            startActivity(i);  // 얘는 결과를 주기만함
        }else if(v.getId() == R.id.buttonThird){
            Intent i = new Intent(this, ThridActivity.class);
            startActivityForResult(i, REQ_THIRD); //REQ_THIRD onActivityResult의 첫번쨰 파라미터로 날라감
        }else if(v.getId() == R.id.buttonPhoto){
            Intent i = new Intent(this, PhotoActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){ // startActivityForResult는 결과를 주고 받기도 하니 이렇게 Override함
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_THIRD){
            String resultStr = "Result Code: " + resultCode;  //resultCode는 true : -1 // false: 0
            if(data != null){
                String result = data.getStringExtra("result");
                if(result != null)
                    resultStr+=(", " + result);
            }
            Toast.makeText(this, resultStr, Toast.LENGTH_SHORT).show();
        }

    }
}
