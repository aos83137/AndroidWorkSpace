package com.example.yongseok.questionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    TextView title, date, type;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleCard);
        date = itemView.findViewById(R.id.saveDate);
        type = itemView.findViewById(R.id.type);
    }
}
