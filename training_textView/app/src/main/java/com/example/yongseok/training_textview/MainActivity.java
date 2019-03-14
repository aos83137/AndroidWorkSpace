package com.example.yongseok.training_textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private EditText a_text, b_text;
    private Button sumButton, subButton, mulButton, divButton;
    private int a, b;
    private float result;
    private String textA, textB, testR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.result);
        a_text = findViewById(R.id.a_text);
        b_text = findViewById(R.id.b_text);
        sumButton = findViewById(R.id.sumButton);
        subButton = findViewById(R.id.subButton);
        mulButton = findViewById(R.id.mulButton);
        divButton = findViewById(R.id.divButton);

        sumButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
        divButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        textA = a_text.getText().toString();
        textB = b_text.getText().toString();
        if (textA.length() != 0 && textB.length() != 0) {
            a = Integer.parseInt(textA);
            b = Integer.parseInt(textB);
        } else {
            textview.setText("a,b에 숫자를 입력 해야 합니다!");
            return;
        }

        if (v.getId() == R.id.sumButton) {
            result = a + b;
        } else if (v.getId() == R.id.subButton) {
            result = a - b;
        } else if (v.getId() == R.id.mulButton) {
            result = a * b;
        } else if (v.getId() == R.id.divButton) {
            if (b == 0) {
                textview.setText("0으로 못나눠요!!");
                return;
            }
            result = a / b;
        }
        testR = "" + result;
        textview.setText(testR);
    }
}
