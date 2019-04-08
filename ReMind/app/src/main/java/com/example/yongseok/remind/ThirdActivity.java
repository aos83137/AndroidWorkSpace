package com.example.yongseok.remind;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ThirdActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        button = findViewById(R.id.button);
        setResult(Activity.RESULT_CANCELED); // 없으면 자동인가?
        //setResult없으면 자동으로 Activity.RESULT_CANCELED (값 0임)이 전달됨
        //setResult(123123); //이렇게 하면 this 액티비티를 호출한 액티비티한테 123123을 전달함 onActivityResult의 resultCode가 받아옴

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("result", "OK");
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

        Intent i = getIntent();
        String test = i.getType();
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
        if(i.getType()!=null && i.getType().equals("text/plain")){
            setResult(Activity.RESULT_OK);
            String text = i.getStringExtra(Intent.EXTRA_TEXT);
            if(text != null){
                TextView tv = findViewById(R.id.textView);
                tv.setText(text);
            }
        }
    }
}
