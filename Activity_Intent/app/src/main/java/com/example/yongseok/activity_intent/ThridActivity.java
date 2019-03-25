package com.example.yongseok.activity_intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ThridActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.button_finish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
