package com.example.yongseok.mymemoapp;

import android.preference.PreferenceActivity;

public class MemoBean {
    private  int sequenceNumber;
    private String memo_head;
    private String memo_body;

    public void setMemo_head(String memo_head) {
        this.memo_head = memo_head;
    }

    public void setMemo_body(String memo_body) {
        this.memo_body = memo_body;
    }

    public String getMemo_head() {
        return memo_head;
    }

    public String getMemo_body() {
        return memo_body;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
}
