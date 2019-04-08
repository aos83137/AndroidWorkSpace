package com.example.yongseok.mymemoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MemoItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewSequence;
    TextView textViewName;

    public MemoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewSequence = itemView.findViewById(R.id.textViewSequence);
        textViewName = itemView.findViewById(R.id.textViewName);
    }
}

