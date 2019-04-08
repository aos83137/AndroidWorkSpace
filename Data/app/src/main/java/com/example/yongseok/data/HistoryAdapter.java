package com.example.yongseok.data;

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

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryItemViewHolder> {

    private ArrayList<UserBean> userData;
    private ItemClickListener listener;

    public HistoryAdapter(ArrayList<UserBean> data, ItemClickListener listener) {
        userData = data;
        this.listener = listener;
    }


    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_history_card, viewGroup, false);
        return new HistoryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, int i) {
        UserBean userBean = userData.get(i);
        historyItemViewHolder.textViewName.setText(userBean.getName());
        historyItemViewHolder.textViewSequence.setText(String.valueOf(userBean.getSequenceNumber()));
        final int index = i;
        historyItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, index);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (userData == null)
            return 0;
        else
            return userData.size();
    }
}


