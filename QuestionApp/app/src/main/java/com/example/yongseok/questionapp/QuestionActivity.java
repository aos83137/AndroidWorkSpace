package com.example.yongseok.questionapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    final int REQ_CODE_SELECT_IMAGE1 = 101;
    final int REQ_CODE_SELECT_IMAGE2 = 102;
    final int REQ_CODE_SELECT_IMAGE3 = 103;
    final int REQ_CODE_SELECT_IMAGE4 = 104;

    private ToggleButton toggleButton;
    private Button saveBtn, deleteBtn;
    private EditText problemEdit, scoringEdit, answer1Edit, answer2Edit, answer3Edit, answer4Edit;
    private ImageButton answer1Img, answer2Img, answer3Img, answer4Img;
    private ConstraintLayout textQuizWindow, imgQuizWindow;


    private QuestionDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //위젯 아이디 처리
        problemEdit = findViewById(R.id.problem);
        scoringEdit = findViewById(R.id.scoring);

        //toggle 이벤트 처리
        toggleButton = findViewById(R.id.toggleInQuestionApp);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textQuizWindow = findViewById(R.id.textQuizWindow);
                imgQuizWindow = findViewById(R.id.imgQuizWindow);
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
        answer2Img = findViewById(R.id.imageButton2);
        answer2Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIMG(REQ_CODE_SELECT_IMAGE2);
            }
        });
        answer3Img = findViewById(R.id.imageButton3);
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

        //db 처리
        dbHelper = new QuestionDBHelper(this, "questionList", null, 1);
        showQuestion();


    }

    public void goIMG(int req_code){
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, req_code);
    } //갤러리로 날아가는 함수

    public void showQuestion() {
        ArrayList<QuestionBean> questions = dbHelper.getAll();
        for (QuestionBean q : questions) {
            String title = q.getProblem();
            Log.i("MAIN", "[" + q.getSequenceNumber() + "]" +title);
        }
    }

    public void saveQuestion()

    {
        QuestionBean question = new QuestionBean();

        question.setProblem(problemEdit.getText().toString());
        question.setScoring(scoringEdit.getText().toString().trim());

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
                    ImageButton imageButton = findViewById(R.id.imageButton);
                    imageButton.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == REQ_CODE_SELECT_IMAGE2) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageButton imageButton = findViewById(R.id.imageButton2);
                    imageButton.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == REQ_CODE_SELECT_IMAGE3) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageButton imageButton = findViewById(R.id.imageButton3);
                    imageButton.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == REQ_CODE_SELECT_IMAGE4) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap image_bitmap = null;
                try {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageButton imageButton = findViewById(R.id.imageButton4);
                    imageButton.setImageBitmap(image_bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
