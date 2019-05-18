package com.example.yongseok.questionapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuestionActivity extends AppCompatActivity {
    final int REQ_CODE_SELECT_IMAGE1 = 101;
    final int REQ_CODE_SELECT_IMAGE2 = 102;
    final int REQ_CODE_SELECT_IMAGE3 = 103;
    final int REQ_CODE_SELECT_IMAGE4 = 104;
    final int FLAG_YYDATE = 431;
    final int FLAG_TTTIME = 432;

    private ArrayList<QuestionBean> data;
    private ToggleButton toggleButton;
    private Button saveBtn, deleteBtn;
    private EditText problemEdit, scoringEdit, answer1Edit, answer2Edit, answer3Edit, answer4Edit;
    private ImageButton answer1Img, answer2Img, answer3Img, answer4Img;
    private RadioGroup radioGroup;
    private RadioButton txtRadio1, txtRadio2, txtRadio3, txtRadio4, imgRadio1,imgRadio2,imgRadio3,imgRadio4;
    private String imgUli1="",imgUli2="",imgUli3="",imgUli4="";
    private ConstraintLayout textQuizWindow, imgQuizWindow;
    private int sNum;

    //type ==img 라디오버튼 리스너
    private View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imgRadio1){
                imgRadio2.setChecked(false);
                imgRadio3.setChecked(false);
                imgRadio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio2){
                imgRadio1.setChecked(false);
                imgRadio3.setChecked(false);
                imgRadio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio3){
                imgRadio2.setChecked(false);
                imgRadio1.setChecked(false);
                imgRadio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio4){
                imgRadio2.setChecked(false);
                imgRadio3.setChecked(false);
                imgRadio1.setChecked(false);
            }
        }
    };

    private QuestionDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //위젯 아이디 처리
        problemEdit = findViewById(R.id.problem);
        scoringEdit = findViewById(R.id.scoring);
        answer1Edit = findViewById(R.id.txtEdit1);
        answer2Edit = findViewById(R.id.txtEdit2);
        answer3Edit = findViewById(R.id.txtEdit3);
        answer4Edit = findViewById(R.id.txtEdit4);
        answer1Img = findViewById(R.id.imageButton);
        answer2Img = findViewById(R.id.imageButton2);
        answer3Img = findViewById(R.id.imageButton3);
        answer4Img = findViewById(R.id.imageButton4);
        radioGroup = findViewById(R.id.txtRadioGroup);
        imgRadio1 = findViewById(R.id.imgRadio1);
        imgRadio2 = findViewById(R.id.imgRadio2);
        imgRadio3 = findViewById(R.id.imgRadio3);
        imgRadio4 = findViewById(R.id.imgRadio4);
        txtRadio1 = findViewById(R.id.txtRadio1);
        txtRadio2 = findViewById(R.id.txtRadio2);
        txtRadio3 = findViewById(R.id.txtRadio3);
        txtRadio4 = findViewById(R.id.txtRadio4);

        //toggle 이벤트 처리
        toggleButton = findViewById(R.id.toggleInQuestionApp);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textQuizWindow = findViewById(R.id.textQuizWindow);
                imgQuizWindow = findViewById(R.id.imgQuizWindow);
                textQuizWindow.setVisibility(View.VISIBLE);
                imgQuizWindow.setVisibility(View.INVISIBLE);
                if (isChecked) {
                    textQuizWindow.setVisibility(View.VISIBLE);
                    imgQuizWindow.setVisibility(View.INVISIBLE);
                } else {
                    textQuizWindow.setVisibility(View.INVISIBLE);
                    imgQuizWindow.setVisibility(View.VISIBLE);
                }
            }
        });

        //save 이벤트
        saveBtn = findViewById(R.id.saveInQuestionApp);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();

                Toast.makeText(QuestionActivity.this, "저장완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //delete 이벤트
        deleteBtn = findViewById(R.id.delQuestionApp);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.clear(sNum);
                Toast.makeText(QuestionActivity.this, "삭제완료", Toast.LENGTH_SHORT).show();
                showQuestion();
                finish();
            }
        });
        //answer IMG 이벤트
        answer1Img = findViewById(R.id.imageButton);
        answer1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIMG(REQ_CODE_SELECT_IMAGE1);
            }
        });
        answer2Img = findViewById(R.id.imageButton3);
        answer2Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIMG(REQ_CODE_SELECT_IMAGE2);
            }
        });
        answer3Img = findViewById(R.id.imageButton2);
        answer3Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIMG(REQ_CODE_SELECT_IMAGE3);
            }
        });
        answer4Img = findViewById(R.id.imageButton4);
        answer4Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIMG(REQ_CODE_SELECT_IMAGE4);
            }
        });


        // radio 버튼 처리..
        imgRadio1 = findViewById(R.id.imgRadio1);
        imgRadio2 = findViewById(R.id.imgRadio2);
        imgRadio3 = findViewById(R.id.imgRadio3);
        imgRadio4 = findViewById(R.id.imgRadio4);

        imgRadio1.setOnClickListener(radioListener);
        imgRadio2.setOnClickListener(radioListener);
        imgRadio3.setOnClickListener(radioListener);
        imgRadio4.setOnClickListener(radioListener);


        //db 처리
        dbHelper = new QuestionDBHelper(this, "questionList", null, 1);
        showQuestion();


        //card클릭으로 호출했을 경우
        sNum = getIntent().getIntExtra("sNum", -1);
        if(sNum != -1){

            QuestionBean bean = null;
            data = dbHelper.getAll();

            for(int i=0;i<data.size();i++){
                if(data.get(i).getSequenceNumber() == sNum){
                    bean = data.get(i);
                }
            }
            problemEdit.setText(bean.getProblem());
            scoringEdit.setText(bean.getScoring());

            //type에 따라 달라짐
            if(bean.getType().equals(QuestionBean.IMG)){ //img
                toggleButton.setChecked(false);

                switch (bean.getAnswerNum()){
                    case 1:
                        imgRadio1.setChecked(true);
                        break;
                    case 2:
                        imgRadio2.setChecked(true);
                        break;
                    case 3:
                        imgRadio3.setChecked(true);
                        break;
                    case 4:
                        imgRadio4.setChecked(true);
                        break;
                }
                answer1Img.setImageURI(Uri.parse(bean.getAnswer1()));
                answer2Img.setImageURI(Uri.parse(bean.getAnswer2()));
                answer3Img.setImageURI(Uri.parse(bean.getAnswer3()));
                answer4Img.setImageURI(Uri.parse(bean.getAnswer4()));
                Log.i("MAIN", "IMG형식 잘나옴");
            }else if(bean.getType().equals(QuestionBean.TEXT)){ // text
                toggleButton.setChecked(true);

                switch (bean.getAnswerNum()) {
                    case 1:
                        txtRadio1.setChecked(true);
                        break;
                    case 2:
                        txtRadio2.setChecked(true);
                        break;
                    case 3:
                        txtRadio3.setChecked(true);
                        break;
                    case 4:
                        txtRadio4.setChecked(true);
                        break;
                }
                answer1Edit.setText(bean.getAnswer1());
                answer2Edit.setText(bean.getAnswer2());
                answer3Edit.setText(bean.getAnswer3());
                answer4Edit.setText(bean.getAnswer4());
                Log.i("MAIN", "TEXT형식 잘나옴");
            }else{
                Log.i("MAIN", "아무것도 없이 걍 나옴..");
            }

        }
    }

    //DATE 처리 함수
    public String generationTime(int flag) {
        Date date = new Date();

        SimpleDateFormat yyDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ttTime = new SimpleDateFormat("k:mm:ss");
        Log.i("MAIN", ttTime.format(date));
        Log.i("MAIN", yyDate.format(date));

        if (flag == FLAG_TTTIME) {
            return ttTime.format(date);
        } else if (flag == FLAG_YYDATE) {
            return yyDate.format(date);
        } else {
            return "시간 저장 실패";
        }

    }

    public void goIMG(int req_code) {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, req_code);
    } //갤러리로 날아가는 함수

    //question list출력
    public void showQuestion() {
        ArrayList<QuestionBean> questions = dbHelper.getAll();
        for (QuestionBean q : questions) {
            String title = q.getProblem();
            Log.i("MAIN", "[" + q.getSequenceNumber() + "]" + title + "스코어 : "+q.getScoring() +" 정답번호:" +q.getAnswerNum() +"타입:  " + q.getType() );
        }
    }

    //question 저장
    public void saveQuestion() {
        QuestionBean question = new QuestionBean();

        if (toggleButton.isChecked()) { //text
            question.setAnswer1(answer1Edit.getText().toString());
            question.setAnswer2(answer2Edit.getText().toString());
            question.setAnswer3(answer3Edit.getText().toString());
            question.setAnswer4(answer4Edit.getText().toString());

            if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio1) question.setAnswerNum(1);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio2) question.setAnswerNum(2);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio3) question.setAnswerNum(3);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio4) question.setAnswerNum(4);
            else question.setAnswerNum(-1);

            question.setType(QuestionBean.TEXT);
        } else { // img
            question.setAnswer1(imgUli1);
            question.setAnswer2(imgUli2);
            question.setAnswer3(imgUli3);
            question.setAnswer4(imgUli4);

            if(imgRadio1.isChecked()) question.setAnswerNum(1);
            else if(imgRadio2.isChecked()) question.setAnswerNum(2);
            else if(imgRadio3.isChecked()) question.setAnswerNum(3);
            else if(imgRadio4.isChecked()) question.setAnswerNum(4);
            else question.setAnswerNum(-1);

            question.setType(QuestionBean.IMG);
        }


        question.setProblem(problemEdit.getText().toString());
        question.setScoring(scoringEdit.getText().toString().trim());
        question.setScoring(scoringEdit.getText().toString().trim());
        question.setTtTime(generationTime(FLAG_TTTIME));
        question.setYyDate(generationTime(FLAG_YYDATE));

        if(sNum==-1) {
            dbHelper.insert(question);
        }else{
            dbHelper.update(sNum,question);
        }
        showQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SELECT_IMAGE1) {
            if (resultCode == Activity.RESULT_OK) {
                imgUli1 = data.getData().toString();
                answer1Img.setImageURI(data.getData());
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE2) {
            if (resultCode == Activity.RESULT_OK) {
                imgUli2 = data.getData().toString();
                answer2Img.setImageURI(data.getData());
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE3) {
            if (resultCode == Activity.RESULT_OK) {
                imgUli3 = data.getData().toString();
                answer3Img.setImageURI(data.getData());
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE4) {
            if (resultCode == Activity.RESULT_OK) {
                imgUli4 = data.getData().toString();
                answer4Img.setImageURI(data.getData());
            }
        }
    }
}
