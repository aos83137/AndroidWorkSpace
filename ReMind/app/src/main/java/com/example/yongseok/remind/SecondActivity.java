package com.example.yongseok.remind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView center_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        Intent i = getIntent();
//        String text = i.getStringExtra("message"); 이렇게 해도됨

        String text = getIntent().getStringExtra("message"); // getintent 반환값이Intent임
        if(text.length() > 0){
            center_textview = findViewById(R.id.textView);
            center_textview.setText(text);
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
