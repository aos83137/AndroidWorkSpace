package com.example.yongseok.mymemoapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemoActivity extends AppCompatActivity  {
    private MemoDBHelper helper;
    private TextView head_text, body_text;
    private  ArrayList<MemoBean> memos = helper.getAll();
    private void showUser(){
        ArrayList<MemoBean> memos = helper.getAll();
        for(MemoBean m : memos){
            Log.i("MAIN", "["+m.getSequenceNumber()+":"+m.getMemo_head()+"]");
        }
//        Log.i("MAIN", "["+memos.get(1).getSequenceNumber()+":"+memos.get(1).getMemo_head()+"]");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
//        if(getIntent())
        helper = new MemoDBHelper(this,"memodb", null,1);

        head_text = findViewById(R.id.text_head);
        body_text = findViewById(R.id.text_body);

    }

    public void saveMemo(View v){
        String head_memo = head_text.getText().toString().trim();
        String body_memo = body_text.getText().toString().trim();
        if(head_memo.length()==0){
            Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
        }
        MemoBean memo = new MemoBean();
        memo.setMemo_head(head_memo);
        memo.setMemo_body(body_memo);
        helper.insert(memo);
        Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show();
        showUser();
        finish();
    }

    public void clearMemo(View v){
        head_text.setText("");
        body_text.setText("");
        Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show();
    }

}
