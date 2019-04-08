package com.example.yongseok.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class History_Activity extends AppCompatActivity implements ItemClickListener {
    private HistoryDBHelper helper;
    private RecyclerView historyList;
    private HistoryAdapter adapter;
    private ArrayList<UserBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_);
        helper = new HistoryDBHelper(this, "userdb", null, 1);
        data = helper.getAll();
        adapter = new HistoryAdapter(data, this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 가로
        GridLayoutManager LayoutManger = new GridLayoutManager(this, 3);
        historyList = findViewById(R.id.historyList);
        historyList.setLayoutManager(LayoutManger);
        historyList.setAdapter(adapter);
    }

    public void onClear(View v) {
        helper.delete();
        data.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int position) {
//        helper.delete(data.get(position));
        data.remove(position);
        adapter.notifyDataSetChanged();
    }
}
