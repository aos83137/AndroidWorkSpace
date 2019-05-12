package com.example.yongseok.questionapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionListActivity extends AppCompatActivity implements ClickListener {
    private static final String sysPasswd = "1541";
    private static final int REQ_QUESTION = 1234;

    private EditText get_passwd;
    private Button sys_signIn;
    private ImageView plusQuizBtn;
    private ConstraintLayout logWindow,sysWindow;

    private QuestionDBHelper helper;
    private ArrayList<QuestionBean> data;
    private RecyclerView questionList;
    private QuizAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        //signIn 이벤트 처리
        get_passwd  = findViewById(R.id.passwdEdit);
        sys_signIn = findViewById(R.id.sys_in_button);
        sys_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwd = get_passwd.getText().toString();
                if(passwd.equals(sysPasswd)){
                    sysWindow = findViewById(R.id.sysWindow);
                    sysWindow.setVisibility(View.VISIBLE);
                    logWindow = findViewById(R.id.loginWindow);
                    logWindow.setVisibility(View.GONE);
                }else{
                    Toast.makeText(QuestionListActivity.this, "비밀번호가 틀렸습니다.!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //plusQuizBtn 이미지 클릭
        plusQuizBtn = findViewById(R.id.plusQuizBtn);
        plusQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
                startActivityForResult(intent, REQ_QUESTION);
            }
        });


        //list처리
        helper = new QuestionDBHelper(this, "questionList", null, 1);
        data = helper.getAll();
        adapter = new QuizAdapter(data, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        questionList = findViewById(R.id.questionList);
        questionList.setLayoutManager(layoutManager);
        questionList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQ_QUESTION){

            }
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
