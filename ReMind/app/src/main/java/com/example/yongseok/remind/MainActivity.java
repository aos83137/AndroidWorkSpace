package com.example.yongseok.remind;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button second_button, third_button;
    private View.OnClickListener change_activity_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.second_button){
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("message", "hello Intent");
                startActivity(i);

            }else if(v.getId() == R.id.third_button){
                Intent i = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(i, REQ_THIRD);
            }
        }
    };
    private static final int REQ_THIRD = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        second_button = findViewById(R.id.second_button);
        third_button  = findViewById(R.id.third_button);
        second_button.setOnClickListener(change_activity_listener);
        third_button.setOnClickListener(change_activity_listener);
        findViewById(R.id.toSmsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("smsto:010-7225-9161"));//Uri로 보내야해서 parse함
                i.putExtra("sms_body", "hello this is Intent");
                startActivity(Intent.createChooser(i, "Select one")); //Intent createChooser (Intent target, CharSequence title)
                //title은 설명임 어떻게 하라는 설명을 적자 -> 그래서 Select one임
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode가 REQ_THIRD를 받아옴
        if(requestCode == REQ_THIRD){
            String resultStr = "Result Code: " + resultCode;
            if(data != null){
                String result = data.getStringExtra("result");
                if(result != null){
                    resultStr += (", " + result);
                }
            }
            Toast.makeText(this, resultStr, Toast.LENGTH_SHORT).show();
        }

    }
}
