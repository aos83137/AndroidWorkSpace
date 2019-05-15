package com.example.yongseok.questionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button startBtn;
    private ImageView setting;
    private TextView textView;
    private int flag=-1;
    public static final int LEVEL_EASY = 123;
    public static final int LEVEL_HARD= 124;

    private View.OnClickListener start_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            if(flag > 0) {
                //putExtra  intent 전달
                intent.putExtra("level_state", flag);

                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "난이도를 선택해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //radioGroup 이벤트처리
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.level_textView1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId ==R.id.easy_radioButton){
                    flag = LEVEL_EASY;
                    textView.setText("Easy 선택");
                }else if (checkedId == R.id.hard_radioButton){
                    flag = LEVEL_HARD;
                    textView.setText("Hard 선택");
                }
                Log.i("MAIN", ""+flag);
            }
        });//버튼 클릭시 flag에 현재 상태 저장

        //startBtn 이벤트 처리
        startBtn = findViewById(R.id.startBtnInMain);
        startBtn.setOnClickListener(start_listener);

        //setting image 이벤트 처리
        setting = findViewById(R.id.setBtnInMain);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });
    }


}
