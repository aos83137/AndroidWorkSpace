package com.example.yongseok.mymemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {
    private Button add_memo;
    private MemoDBHelper helper;
    private ArrayList<MemoBean> data;
    private MemoAdapter adapter;
    private RecyclerView memoList;
    private static final int REQ_MEMO = 123;
    public static final int FLAG = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MemoDBHelper(this, "memodb", null, 1);
        data = helper.getAll();
        adapter = new MemoAdapter(data, this, this);//자기자신Context날리기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 가로
        memoList = findViewById(R.id.memoList);
        memoList.setLayoutManager(layoutManager);
        memoList.setAdapter(adapter);

        add_memo = findViewById(R.id.add_memo);
        add_memo.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View v, int position, Context context) { // 여기가 item 눌렀을때 event임 // position은 item id라 보자
        Intent  i = new Intent(context, MemoActivity.class);
        MemoBean item_memo = data.get(position);
        i.putExtra("item_sequence", item_memo.getSequenceNumber());
        i.putExtra("item_head", item_memo.getMemo_head());
        i.putExtra("item_body", item_memo.getMemo_body());
//                context.startActivity(i);
        i.putExtra("flag", FLAG);
        startActivityForResult(i,REQ_MEMO);
        Log.i("Check", "this item index : " + position);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MemoActivity.class);
        startActivityForResult(i,REQ_MEMO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.recreate();
    }
}
