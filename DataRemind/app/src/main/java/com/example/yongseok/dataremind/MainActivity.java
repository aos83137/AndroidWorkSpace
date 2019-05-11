package com.example.yongseok.dataremind;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login, logout;
    private TextView textView; //home화면
    private EditText editText; //form화면
    private int[] layoutIds = {R.id.form, R.id.home};
    private LinearLayout[] layouts;
    private SharedPreferences sp;

    private void showLayout(int id) {
        for (LinearLayout l : layouts) {
            if (l.getId() == id)
                l.setVisibility(View.VISIBLE);
            else
                l.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layouts = new LinearLayout[layoutIds.length];
        for (int i = 0; i < layouts.length; i++) {
            layouts[i] = findViewById(layoutIds[i]);
        }
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        String userName = sp.getString("userName", null);
        if(userName != null) {
            textView.setText(userName);
            showLayout(R.id.home);
        }else{
            showLayout(R.id.form);
        }
    }

    public void onLogin(View v) {
        if (sp == null) return;
        String name = editText.getText().toString().trim();
        if (name.length() == 0) return;

        SharedPreferences.Editor editor = sp.edit();  //Preference의 edit을 구현 왜?써먹어야하니깐
        editor.putString("userName", name);
        editor.putString("testPut", "더들어가냐?더더");
        String tt = sp.getString("testPut", null);
        Toast.makeText(this, tt, Toast.LENGTH_SHORT).show();
        editor.commit();

        textView.setText(name);
        showLayout(R.id.home);
    }

    public void onLogout(View v) {
        if (sp == null) return;
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("userName");
        editor.apply();
        showLayout(R.id.form);
    }
}

