package com.example.yongseok.activity_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonSecond).setOnClickListener(this);
        findViewById(R.id.buttonThird).setOnClickListener(this);
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
            startActivity(i);
        }else if(v.getId() == R.id.buttonThird){
            Intent i = new Intent(this, ThridActivity.class);
            startActivity(i);
        }
    }
}
