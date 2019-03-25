package com.example.yongseok.activity_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private View.OnClickListener buttonListener = v -> {
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.button_finish).setOnClickListener(buttonListener);
        String message = getIntent().getStringExtra("message");
        if(message!=null){
            TextView textView = findViewById(R.id.textView);
            textView.setText(message);
        }
    }
}
