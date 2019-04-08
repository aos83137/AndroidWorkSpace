package com.example.yongseok.data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private int[] layoutIds = {R.id.layoutForm, R.id.layoutHome}; //R.id.~~~~이거 int였구나!!!
    private LinearLayout[] layouts;
    private EditText editTextUserName;
    private TextView textViewUserName;
    private SharedPreferences preferences;
    private HistoryDBHelper dbHelper;
    private void showLayout(int id) {
        for (LinearLayout layout : layouts) {
            if (layout.getId() == id)
                layout.setVisibility(View.VISIBLE);
            else
                layout.setVisibility(View.GONE);
        }
    }
    private void showUsers(){
        ArrayList<UserBean> users = dbHelper.getAll();
        for(UserBean u: users)
            Log.i("MAIN", "["+u.getSequenceNumber()+"]" + u.getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new HistoryDBHelper(this, "userdb", null, 1);
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
        showUsers();
    }

    public void onLogin(View v) {
        if (preferences == null) return; // 이름 없을때
        String userName = editTextUserName.getText().toString().trim();
        if (userName.length() == 0) return;  // editText아무것도 없다 그럼  리터너
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName", userName);
        editor.apply();
        textViewUserName.setText(userName);
        showLayout(R.id.layoutHome);

        UserBean user = new UserBean();
        user.setName(userName);
        dbHelper.insert(user);
        showUsers();
    }

    public void onLogout(View v) {
        if (preferences == null) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userName"); // or editor.clear(); //하나는 remove("") 전체 삭제는 editor.clear()사용
        editor.apply();
        showLayout(R.id.layoutForm);
    }

    public void onHistory(View v){
        startActivity(new Intent(this, History_Activity.class));
    }
}

// SharedPreferences.Editor editor  SharedPreferences 객체의 값을 수정하는 데 사용되는 인터페이스입니다. 에디터에서 변경 한 모든 내용은 일괄 처리되어 commit () 또는 apply ()를 호출 할 때까지 원래 SharedPreferences로 다시 복사되지 않습니다.