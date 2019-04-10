package com.example.yongseok.mymemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoItemViewHolder> {

    private ArrayList<MemoBean> memoData;
    private ItemClickListener listener;

    public MemoAdapter(ArrayList<MemoBean> data, ItemClickListener listener) {
        memoData = data;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MemoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_memo_card, viewGroup, false);
        return new MemoItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoItemViewHolder memoItemViewHolder, int i) {
        MemoBean memoBean = memoData.get(i);
        memoItemViewHolder.textViewSequence.setText(String.valueOf(memoBean.getSequenceNumber()));
        memoItemViewHolder.textViewName.setText(memoBean.getMemo_head());
        final int index = i;
        memoItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent()
                listener.onItemClick(v, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (memoData == null)
            return 0;
        else
            return memoData.size();
    }
}


