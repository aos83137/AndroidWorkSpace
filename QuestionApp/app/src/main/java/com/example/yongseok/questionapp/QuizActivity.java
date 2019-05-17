package com.example.yongseok.questionapp;

import android.Manifest;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ConstraintLayout easyLayout, hardLayout_txt, hardLayout_img;
    private ArrayList<QuestionBean> quizs;
    private QuestionBean bean;
    private QuestionDBHelper dbHelper;
    private int score;
    private TextView problemView, problemNumView, score_view;
    private int level, cnt;
    //easy 위젯
    private RadioButton easy_radio1, easy_radio2, easy_radio3, easy_radio4, img_radio1, img_radio2, img_radio3, img_radio4;
    //hard_img 위젯
    private ImageView hard_img_view1, hard_img_view2, hard_img_view3, hard_img_view4;
    //hard_txt 위젯
    private EditText hard_txt_edit1;
    private Button resultSubBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //위젯 findView
        easyLayout = findViewById(R.id.easyMode);
        hardLayout_txt = findViewById(R.id.hardMode_txt);
        hardLayout_img = findViewById(R.id.hardMode_img);
        problemView = findViewById(R.id.problem_view);
        problemNumView = findViewById(R.id.problemNum_view);
        score_view = findViewById(R.id.score_view);
        easy_radio1 = findViewById(R.id.easy_radio1);
        easy_radio2 = findViewById(R.id.easy_radio2);
        easy_radio3 = findViewById(R.id.easy_radio3);
        easy_radio4 = findViewById(R.id.easy_radio4);
        img_radio1 = findViewById(R.id.hard_img_radio1);
        img_radio2 = findViewById(R.id.hard_img_radio2);
        img_radio3 = findViewById(R.id.hard_img_radio3);
        img_radio4 = findViewById(R.id.hard_img_radio4);
        hard_img_view1 = findViewById(R.id.hard_img_view1);
        hard_img_view2 = findViewById(R.id.hard_img_view2);
        hard_img_view3 = findViewById(R.id.hard_img_view3);
        hard_img_view4 = findViewById(R.id.hard_img_view4);
        hard_txt_edit1 = findViewById(R.id.hard_txt_edit1);
        resultSubBtn = findViewById(R.id.resultSubBtn);

        level = getIntent().getIntExtra("level_state", -1);
        score = 0;// onCreate시 score 초기화
        cnt = 0;

        dbHelper = new QuestionDBHelper(this, "questionList", null, 1);
        quizs = dbHelper.getAll();

        bean = quizs.get(cnt);
        viewProblem(bean);

        resultSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                //hard text 정답시
                if (level == MainActivity.LEVEL_HARD && bean.getType().equals(QuestionBean.TEXT)) {
                    Log.i("FLAGTEST", "하드, 텍스트");
                    checkResultText_hard();
                } else if (level == MainActivity.LEVEL_EASY && bean.getType().equals(QuestionBean.TEXT)) {
                    Log.i("FLAGTEST", "이지, 텍스트");
                    checkResultText_easy();
                } else if (bean.getType().equals(QuestionBean.IMG)) {
                    Log.i("FLAGTEST", "이미지 객관식");
                    checkResultImg();
                }
                //

                Log.i("FLAGTEST", "size : " + (quizs.size() - 1) + "  cnt : " + cnt);
                if (quizs.size() == cnt) {//여기다가 팝업 만들기
                    Toast.makeText(QuizActivity.this, "총 스코어 : " + score, Toast.LENGTH_SHORT).show();
                    finish();
                    return;  //return 넣은 이유 finsh만하니까 finish함수 끝나기전에 밑에 명령어를 실행하게 되어서 bean = quizs.get(cnt)를 실행하게 됨 그래서 오류나서 return으로 멈췄음
                }else{
                    bean = quizs.get(cnt);
                    radioCheckRls();

                    viewProblem(bean);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void viewProblem(QuestionBean bean) {
        //problem, scoring 등 set

        problemView.setText(bean.getProblem());
        problemNumView.setText("" + (cnt + 1));
        score_view.setText("" + score);
        //현 문제의 배점

        if (bean.getType().equals(QuestionBean.IMG)) { // 이미지 - 개관식
            //레이아웃 viisible
            hardLayout_img.setVisibility(View.VISIBLE);
            hardLayout_txt.setVisibility(View.GONE);
            easyLayout.setVisibility(View.GONE);

            hard_img_view1.setImageURI(Uri.parse(bean.getAnswer1()));
            hard_img_view2.setImageURI(Uri.parse(bean.getAnswer2()));
            hard_img_view3.setImageURI(Uri.parse(bean.getAnswer3()));
            hard_img_view4.setImageURI(Uri.parse(bean.getAnswer4()));

        } else if (level == MainActivity.LEVEL_EASY && bean.getType().equals(QuestionBean.TEXT)) { //이지모드 텍스트 - 객관식
            //레이아웃 viisible
            easyLayout.setVisibility(View.VISIBLE);
            hardLayout_img.setVisibility(View.GONE);
            hardLayout_txt.setVisibility(View.GONE);
            easy_radio1.setText(bean.getAnswer1());
            easy_radio2.setText(bean.getAnswer2());
            easy_radio3.setText(bean.getAnswer3());
            easy_radio4.setText(bean.getAnswer4());

        } else if (level == MainActivity.LEVEL_HARD && bean.getType().equals(QuestionBean.TEXT)) { //하드모드 텍스트 - 주관식

            //레이아웃 viisible
            hardLayout_txt.setVisibility(View.VISIBLE);
            hardLayout_img.setVisibility(View.GONE);
            easyLayout.setVisibility(View.GONE);


        }
    }

    //radio버튼 초기화
    public void radioCheckRls() {
        easy_radio1.setChecked(false);
        easy_radio2.setChecked(false);
        easy_radio3.setChecked(false);
        easy_radio4.setChecked(false);
        img_radio1.setChecked(false);
        img_radio2.setChecked(false);
        img_radio3.setChecked(false);
        img_radio4.setChecked(false);
    }

    //db에 정답번호와 같은 답을 return하는 함수
    public String getAnswerHard() {
        if (bean.getAnswerNum() == 1) {
            return bean.getAnswer1();
        } else if (bean.getAnswerNum() == 2) {
            return bean.getAnswer2();
        } else if (bean.getAnswerNum() == 3) {
            return bean.getAnswer3();
        } else if (bean.getAnswerNum() == 4) {
            return bean.getAnswer4();
        }
        return "error";
    }

    //
    public void checkResultText_hard() {
        int quiz_score = Integer.parseInt(bean.getScoring());
        String user_answer = hard_txt_edit1.getText().toString().trim();
        String P_answer;

        P_answer = getAnswerHard();
        Log.i("FLAGTEST", P_answer + "  " + user_answer);
        if (P_answer.equals(user_answer)) {  // 정답시 score 증가
            score += quiz_score;
            Toast.makeText(QuizActivity.this, "삥퐁~! 정답입니다~~ " + quiz_score + "점 획득", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, "삐빅~! 틀렸습니다~~", Toast.LENGTH_SHORT).show();
        }
        hard_txt_edit1.setText("");
    }

    public void checkResultImg() {
        int quiz_score = Integer.parseInt(bean.getScoring());
        RadioGroup radioGroup = findViewById(R.id.hard_img_radioGroup);
        int idx_img = groupCheckedIndex(radioGroup);

        if (idx_img + 1 == bean.getAnswerNum()) {
            score += quiz_score;
            Log.i("FLAGTEST", "이미지더하기 실행"+score);
            Toast.makeText(QuizActivity.this, "삥퐁~! 정답입니다~~ " + quiz_score + "점 획득", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, "삐빅~! 틀렸습니다~~", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkResultText_easy() {
        int quiz_score = Integer.parseInt(bean.getScoring());
        RadioGroup radioGroup = findViewById(R.id.easy_radioGroup);
        int idx_img = groupCheckedIndex(radioGroup);

        if (idx_img + 1 == bean.getAnswerNum()) {
            score += quiz_score;
            Log.i("FLAGTEST", "이미지더하기 실행"+score);
            Toast.makeText(QuizActivity.this, "삥퐁~! 정답입니다~~ " + quiz_score + "점 획득", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, "삐빅~! 틀렸습니다~~", Toast.LENGTH_SHORT).show();
        }

    }

    //radio 체크 index 구하는 함수
    public int groupCheckedIndex(RadioGroup r) {
        int radioId = r.getCheckedRadioButtonId();
        View radioButton = r.findViewById(radioId);
        int idx = r.indexOfChild(radioButton);
        return idx;
    }
}
