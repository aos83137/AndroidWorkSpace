package com.example.yongseok.widget3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Button button;
    private TextView textView_name, textView_email;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private CheckBox checkBoxes1, checkBoxes2, checkBoxes3, checkBoxes4, checkBoxes5, checkBoxes6, checkBoxes7, checkBoxes8, checkBoxes9;
    private int cnt = 0;
    private int checkBoxCnt = 0;
    private View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            cnt++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        textView_email = findViewById(R.id.emailText);
        textView_name = findViewById(R.id.nameText);

        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton1.setOnClickListener(radioListener);
        radioButton2.setOnClickListener(radioListener);
        radioButton3.setOnClickListener(radioListener);

        checkBoxes1 = findViewById(R.id.checkBox1);
        checkBoxes2 = findViewById(R.id.checkBox2);
        checkBoxes3 = findViewById(R.id.checkBox3);
        checkBoxes4 = findViewById(R.id.checkBox4);
        checkBoxes5 = findViewById(R.id.checkBox5);
        checkBoxes6 = findViewById(R.id.checkBox6);
        checkBoxes7 = findViewById(R.id.checkBox7);
        checkBoxes8 = findViewById(R.id.checkBox8);
        checkBoxes9 = findViewById(R.id.checkBox9);

        checkBoxes1.setOnCheckedChangeListener(this);
        checkBoxes2.setOnCheckedChangeListener(this);
        checkBoxes3.setOnCheckedChangeListener(this);
        checkBoxes4.setOnCheckedChangeListener(this);
        checkBoxes5.setOnCheckedChangeListener(this);
        checkBoxes6.setOnCheckedChangeListener(this);
        checkBoxes7.setOnCheckedChangeListener(this);
        checkBoxes8.setOnCheckedChangeListener(this);
        checkBoxes9.setOnCheckedChangeListener(this);

    }


    @Override
    public void onClick(View v) {
        String text = "";
        if (textView_name.getText().toString().length() == 0) {
            text = "이름";
            Toast.makeText(this, text + "을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (textView_email.getText().toString().length() == 0) {
            text = "이메일";
            Toast.makeText(this, text + "을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (cnt == 0) {
            text = "학년";
            Toast.makeText(this, text + "을 선택하세요", Toast.LENGTH_SHORT).show();
        } else if (checkBoxCnt < 1 || checkBoxCnt > 3) {
            Toast.makeText(this, "동아리는 1개~3개 사이로 선택해 주세요", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "신청되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            checkBoxCnt++;
        } else {
            checkBoxCnt--;
        }
    }
}
