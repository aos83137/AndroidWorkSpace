package com.example.yongseok.questionapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import java.io.FileNotFoundException;
import java.io.IOException;
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

    private ToggleButton toggleButton;
    private Button saveBtn, deleteBtn;
    private EditText problemEdit, scoringEdit, answer1Edit, answer2Edit, answer3Edit, answer4Edit;
    private ImageButton answer1Img, answer2Img, answer3Img, answer4Img;
    private RadioGroup radioGroup;
    private RadioButton radio1, radio2, radio3, radio4;
    private ConstraintLayout textQuizWindow, imgQuizWindow;

    private View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imgRadio1){
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio2){
                radio1.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio3){
                radio2.setChecked(false);
                radio1.setChecked(false);
                radio4.setChecked(false);
            }else if(v.getId() == R.id.imgRadio4){
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio1.setChecked(false);
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
        radio1 = findViewById(R.id.imgRadio1);
        radio2 = findViewById(R.id.imgRadio2);
        radio3 = findViewById(R.id.imgRadio3);
        radio4 = findViewById(R.id.imgRadio4);

        radio1.setOnClickListener(radioListener);
        radio2.setOnClickListener(radioListener);
        radio3.setOnClickListener(radioListener);
        radio4.setOnClickListener(radioListener);


        //db 처리
        dbHelper = new QuestionDBHelper(this, "questionList", null, 1);
        showQuestion();


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
            Log.i("MAIN", "[" + q.getSequenceNumber() + "]" + title + q.getScoring() +"  " +q.getAnswerNum() +"  " + q.getType() );
        }
    }

    //question 저장
    public void saveQuestion() {
        QuestionBean question = new QuestionBean();

        String tBtn;
        if (toggleButton.isChecked()) { //text
            tBtn = toggleButton.getTextOn().toString();
            question.setAnswer1(answer1Edit.getText().toString());
            question.setAnswer1(answer2Edit.getText().toString());
            question.setAnswer1(answer3Edit.getText().toString());
            question.setAnswer1(answer4Edit.getText().toString());

            if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio1) question.setAnswerNum(1);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio2) question.setAnswerNum(2);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio3) question.setAnswerNum(3);
            else if(radioGroup.getCheckedRadioButtonId() == R.id.txtRadio4) question.setAnswerNum(4);
            else question.setAnswerNum(-1);
        } else { // img
            tBtn = toggleButton.getTextOff().toString();

            if(radio1.isChecked()) question.setAnswerNum(1);
            else if(radio2.isChecked()) question.setAnswerNum(2);
            else if(radio3.isChecked()) question.setAnswerNum(3);
            else if(radio4.isChecked()) question.setAnswerNum(4);
            else question.setAnswerNum(-1);
        }


        question.setProblem(problemEdit.getText().toString());
        question.setScoring(scoringEdit.getText().toString().trim());
        question.setType(tBtn);
        question.setScoring(scoringEdit.getText().toString().trim());
        question.setTtTime(generationTime(FLAG_TTTIME));
        question.setYyDate(generationTime(FLAG_YYDATE));


        dbHelper.insert(question);
        showQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SELECT_IMAGE1) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    answer1Img.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE2) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    answer2Img.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE3) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    answer3Img.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE4) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    answer4Img.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
