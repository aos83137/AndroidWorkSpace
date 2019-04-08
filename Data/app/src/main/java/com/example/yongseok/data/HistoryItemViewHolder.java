package com.example.yongseok.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewSequence;
    TextView textViewName;
    public HistoryItemViewHolder(View itemView){
        super(itemView);
        textViewSequence = itemView.findViewById(R.id.textViewSequence);
        textViewName = itemView.findViewById(R.id.textViewName);
    }
}
