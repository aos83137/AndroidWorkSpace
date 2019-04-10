package com.example.yongseok.mymemoapp;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MemoDBHelper(this, "memodb", null, 1);
        data = helper.getAll();
        adapter = new MemoAdapter(data, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 가로
        memoList = findViewById(R.id.memoList);
        memoList.setLayoutManager(layoutManager);
        memoList.setAdapter(adapter);

        add_memo = findViewById(R.id.add_memo);
        add_memo.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View v, int position) { // 여기가 item 눌렀을때 event임 // position은 item id라 보자

//        helper.delete(data.get(position));
//        data.remove(position);

//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MemoActivity.class);
        startActivity(i);
    }
}
