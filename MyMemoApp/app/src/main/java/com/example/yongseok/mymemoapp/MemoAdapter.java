package com.example.yongseok.mymemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoAdapter extends RecyclerView.Adapter<MemoItemViewHolder> { //viewHolder

    private ArrayList<MemoBean> memoData; // data list
    private ItemClickListener listener;
    private Context context;

    public MemoAdapter(ArrayList<MemoBean> data, ItemClickListener listener) {
        memoData = data;
        this.listener = listener;
    }
    public MemoAdapter(ArrayList<MemoBean> data, ItemClickListener listener, Context context) {
        memoData = data;
        this.listener = listener;
        this.context = context;
    }//Context 가져오기 위해서 오버로딩함

    @NonNull
    @Override
    public MemoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) { // ViewHolder 만드는 메서드
        View itemView = LayoutInflater //xml파일을 자바 파일로 변환할 때 쓰는 친구임
                .from(viewGroup.getContext())
                .inflate(R.layout.item_memo_card, viewGroup, false);
        return new MemoItemViewHolder(itemView); // itemView를 return 즉 공급해줌 어디에? -> recycler view에다가!
    }

    @Override
    public void onBindViewHolder(@NonNull MemoItemViewHolder memoItemViewHolder, int i) { //item 값 저장 Data list사용
        MemoBean memoBean = memoData.get(i); // 선택한놈 bean만 저장

        memoItemViewHolder.textViewSequence.setText(String.valueOf(i+1));
/*
        시간 추가 -- Bean이랑 추가 안해서 오류남 나중에 확인,참고
        Date date = new Date(memoBean.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String time = format.format(date);
        memoItemViewHolder.textViewTime.setText(time);
*/

        memoItemViewHolder.textViewName.setText(memoBean.getMemo_head());
        final int index = i;
        memoItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, index, context);
            }
        });
    }

    @Override
    public int getItemCount() { // 왜하는거지??
        if (memoData == null)
            return 0;
        else
            return memoData.size();
    }
}


