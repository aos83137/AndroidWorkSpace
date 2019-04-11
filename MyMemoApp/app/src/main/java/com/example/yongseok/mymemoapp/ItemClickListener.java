package com.example.yongseok.mymemoapp;


import android.content.Context;
import android.view.View;

public interface ItemClickListener {
  public void onItemClick(View v, int position, Context context); //Context 추가
}
