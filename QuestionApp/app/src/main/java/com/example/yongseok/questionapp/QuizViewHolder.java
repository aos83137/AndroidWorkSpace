package com.example.yongseok.questionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    TextView title, type, yyDate, ttTime;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleCard);
        type = itemView.findViewById(R.id.saveDate);
        yyDate = itemView.findViewById(R.id.yyDate);
        ttTime = itemView.findViewById(R.id.ttTime);
    }
}
