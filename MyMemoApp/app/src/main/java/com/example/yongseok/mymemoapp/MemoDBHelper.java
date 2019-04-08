package com.example.yongseok.mymemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class MemoDBHelper extends SQLiteOpenHelper {
    public MemoDBHelper(@Nullable Context context, @Nullable  String name, @Nullable  SQLiteDatabase.CursorFactory factory, int version) {//앱버전과 디비 버전이 다름
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table memoList ( sequenceNumber integer primary key autoincrement, head text, body text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP table memoList";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(MemoBean memo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("head", memo.getMemo_head());
        value.put("body", memo.getMemo_body());
        return db.insert("memoList", null, value);
    }

    public ArrayList<MemoBean> getAll(){
        SQLiteDatabase db = getReadableDatabase();// select는 read동작임
        Cursor cursor = db.query("memoList", null, null, null,
                null, null, null); //이렇게 쓰는건 sqlite만 그럼
        ArrayList<MemoBean> result = new ArrayList<>();
        while(cursor.moveToNext()){ // moveToNext 커서가 다음꺼 확인하고 없으면 false 있으면 true
            MemoBean user = new MemoBean();
            user.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            user.setMemo_head(cursor.getString(cursor.getColumnIndex("head")));
            user.setMemo_body(cursor.getString(cursor.getColumnIndex("body")));

            result.add(user);
        }
        return result;
    }
}
