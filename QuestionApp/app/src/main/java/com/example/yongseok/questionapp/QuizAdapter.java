package com.example.yongseok.questionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {

    private ArrayList<QuestionBean> questionData;
    private ClickListener listener;

    public QuizAdapter(ArrayList<QuestionBean> data, ClickListener listener) {
        questionData = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater //xml파일을 자바 파일로 변환할 때 쓰는 친구임
                .from(viewGroup.getContext())
                .inflate(R.layout.item_quiz_card, viewGroup, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder quizViewHolder, int i) {


        QuestionBean questionBean = questionData.get(i);

        String str = cutString(questionBean.getProblem());//title 출력 조절 함수
        quizViewHolder.title.setText(str);
        quizViewHolder.type.setText(questionBean.getType());
        quizViewHolder.yyDate.setText(questionBean.getYyDate());
        quizViewHolder.ttTime.setText(questionBean.getTtTime());
        final int index = i;
        quizViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (questionData == null) return 0;
        else return questionData.size();
    }

    public String cutString(String str) {
        String text = str;

        if(str.length()>10)
            text = str.substring(0, 10) + "...";

        return text;
    }
}
