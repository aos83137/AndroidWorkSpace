package com.example.yongseok.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class QuizActivity extends AppCompatActivity {

    private int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


    }

    @Override
    protected void onResume() {
        super.onResume();

        level = getIntent().getIntExtra("level_state", -1);

        if(level == MainActivity.LEVEL_EASY){ // 이지모드 전부 객관식

        }else if(level == MainActivity.LEVEL_HARD){ //하드모드 타입:img 객관식 / text 주관식

        }else {
            Log.e("MAIN", "level getIntExtra 부분 잘못됨");
        }
    }
}
