package com.example.yongseok.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private Button button,button2,button3,button4;
    private EditText editText;
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            textView.setText("변수사용해서 만듬!");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        button = findViewById(R.id.activityButton);
        button2 = findViewById(R.id.noNameButton);
        button3 = findViewById(R.id.varButton);
        button4 = findViewById(R.id.myFunctionButton);
        editText = findViewById(R.id.editText);

        textView.setText("Hello!!");
        button.setOnClickListener(this);
        button2.setOnClickListener(view -> textView.setText("람다식, 무명클래스"));
        button3.setOnClickListener(buttonListener);
        button4.setOnClickListener(this::myOnButton);
    }
    @Override
    public void onClick(View v) {
        String text;
        text = editText.getText().toString();
        textView.setText(text);
    }

    public void myOnButton(View v){
        textView.setText("내가 만든 메서드!!");
    }
}
