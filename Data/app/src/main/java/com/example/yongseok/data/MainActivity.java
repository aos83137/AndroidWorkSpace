package com.example.yongseok.data;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int[] layoutIds = {R.id.layoutForm, R.id.layoutHome}; //R.id.~~~~이거 int였구나!!!
    private LinearLayout[] layouts;
    private EditText editTextUserName;
    private TextView textViewUserName;
    private SharedPreferences preferences;

    private void showLayout(int id) {
        for (LinearLayout layout : layouts) {
            if (layout.getId() == id)
                layout.setVisibility(View.VISIBLE);
            else
                layout.setVisibility(View.GONE);
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
        editTextUserName = findViewById(R.id.editTextUserName);
        textViewUserName = findViewById(R.id.textViewUserName);
        preferences = getSharedPreferences("user", MODE_PRIVATE); // 이런 이름이 있으면 공유한다 뭐 그러심
        String userName = preferences.getString("userName", null);
        if (userName == null)
            showLayout(R.id.layoutForm);
        else {
            textViewUserName.setText(userName);
            showLayout(R.id.layoutHome);
        }
    }

    public void onLogin(View v) {
        if (preferences == null) return; // 이름 없을때
        String userName = editTextUserName.getText().toString().trim();
        if (userName.length() == 0) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName", userName);
        editor.apply();
        textViewUserName.setText(userName);
        showLayout(R.id.layoutHome);
    }

    public void onLogout(View v) {
        if (preferences == null) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userName"); // or editor.clear(); //하나는 remove("") 전체 삭제는 editor.clear()사용
        editor.apply();
        showLayout(R.id.layoutForm);
    }
}

