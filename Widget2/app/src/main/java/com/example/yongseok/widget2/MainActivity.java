package com.example.yongseok.widget2;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    private ToggleButton toggleButton;
    private CheckBox checkBox1, checkBox2;
    private RadioButton radioButton1, radioButton2;
    private ProgressBar progressBar1, progressBar2;
    private  SeekBar seekBar1, seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(this);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(this);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton1.setOnClickListener(this);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(this);

        progressBar1 = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);

        seekBar1 = findViewById(R.id.seekBar);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2 = findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, buttonView.getText() + "Status is " + isChecked, Toast.LENGTH_SHORT).show();

        if (buttonView.getId() == R.id.toggleButton)
            if (isChecked) {
                progressBar1.setVisibility(View.VISIBLE);
            } else {
                progressBar1.setVisibility(View.INVISIBLE);
            }//View.GONE 도 있음
    }

    //최상위 클래스 뷰에는 버튼이없다?
    @Override
    public void onClick(View v) {
        RadioButton button = (RadioButton) v;
        Toast.makeText(this, button.getText(), Toast.LENGTH_SHORT).show();
        if(button.getId() == R.id.radioButton){
            progressBar2.incrementProgressBy(1);
        }else if(v.getId() == R.id.radioButton2){
            progressBar2.incrementProgressBy(-1);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            progressBar2.setProgress(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
