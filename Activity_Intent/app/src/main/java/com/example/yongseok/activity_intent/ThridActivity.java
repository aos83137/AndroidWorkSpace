package com.example.yongseok.activity_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ThridActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
//        setResult(Activity.RESULT_CANCELED);
        findViewById(R.id.button_finish).setOnClickListener(this);

        Intent i = getIntent();
        if(i.getType() != null && i.getType().equals("text/plain")){
            setResult(Activity.RESULT_OK);
            String text = i.getStringExtra(Intent.EXTRA_TEXT);
            if(text != null){
                TextView tv = findViewById(R.id.textView);
                tv.setText(text);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.putExtra("result", "OK");
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
