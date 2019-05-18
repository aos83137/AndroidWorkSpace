package com.example.yongseok.questionapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
    private TextView textView,easyView,hardView;
    private int flag = -1;
    public static final int LEVEL_EASY = 123;
    public static final int LEVEL_HARD = 124;
    public static final int REQ_TEST = 337;

    private View.OnClickListener start_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            if (flag > 0) {
                //putExtra  intent 전달
                intent.putExtra("level_state", flag);

                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "난이도를 선택해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyView = findViewById(R.id.easy_textView);
        hardView = findViewById(R.id.hard_textView);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.level_textView1);


        //textView 클릭시 radio true
        easyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radio = findViewById(R.id.easy_radioButton);
                radio.setChecked(true);
            }
        });

        hardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radio = findViewById(R.id.hard_radioButton);
                radio.setChecked(true);
            }
        });

        //radioGroup 이벤트처리
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.easy_radioButton) {
                    flag = LEVEL_EASY;
                    textView.setText("모든 문제가 객관식으로\n 출제됩니다.");
                } else if (checkedId == R.id.hard_radioButton) {
                    flag = LEVEL_HARD;
                    textView.setText("객관식과 주관식이\n 출제됩니다.");
                }
                Log.i("MAIN", "" + flag);
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

        if (Build.VERSION.SDK_INT >= 23) {
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_TEST);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQ_TEST) return;
        if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED);

        else Toast.makeText(this, "문자 전송 권한이 없습니다.", Toast.LENGTH_SHORT).show();
    }
}
