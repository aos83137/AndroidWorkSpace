package com.example.yongseok.mymemoapp;

import android.app.Activity;
import android.content.Intent;
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

public class MemoActivity extends AppCompatActivity {
    private MemoDBHelper helper;
    private TextView head_text, body_text;
    private ArrayList<MemoBean> memos;
    private String item_head, item_body;
    private int item_sequence;
    private int check;

    private void showUser() {
        ArrayList<MemoBean> memos = helper.getAll();
        for (MemoBean m : memos) {
            Log.i("MAIN", "[" + m.getSequenceNumber() + ":" + m.getMemo_head() + "]");
        }
//        Log.i("MAIN", "["+memos.get(1).getSequenceNumber()+":"+memos.get(1).getMemo_head()+"]");
    }

    private MemoBean memo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        helper = new MemoDBHelper(this, "memodb", null, 1);

        head_text = findViewById(R.id.text_head);
        body_text = findViewById(R.id.text_body);

        item_sequence = getIntent().getIntExtra("item_sequence", -1);

        if (item_sequence == -1) {
            memo = new MemoBean();
        } else {
            memo = helper.get(item_sequence);

/*            item_head = memo.getMemo_head();
            item_body = memo.getMemo_body();*/
            item_head = getIntent().getStringExtra("item_head");
            item_body = getIntent().getStringExtra("item_body");

            head_text.setText(item_head);
            body_text.setText(item_body);
        }

//        check = getIntent().getIntExtra("flag",0); 필요없는거 같음

        setResult(Activity.RESULT_CANCELED);
    }

    public void saveMemo(View v) {

        String head_memo = head_text.getText().toString().trim();
        String body_memo = body_text.getText().toString().trim();
        if (head_memo.length() == 0) {
            Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
        }
          /*
           if(helper.insert(memo) > 0){ // 실패하면 음수니까 이렇게 조건 검
            setResult(Activity.RESULT_OK);
            finish();
        }*/


        MemoBean memo = new MemoBean();
        memo.setMemo_head(head_memo);
        memo.setMemo_body(body_memo);
        helper.insert(memo);
        Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show();
        showUser();
        Intent i = new Intent();
        i.putExtra("result", "OK");
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void clearMemo(View v) {
        helper.clear(item_sequence);
        Toast.makeText(this, "삭제완료", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

}
