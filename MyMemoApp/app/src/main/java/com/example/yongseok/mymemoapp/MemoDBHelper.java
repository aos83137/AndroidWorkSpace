package com.example.yongseok.mymemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class MemoDBHelper extends SQLiteOpenHelper {
    private   ArrayList<MemoBean> result;
    public MemoDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {//앱버전과 디비 버전이 다름
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table memoList ( sequenceNumber integer primary key autoincrement, head text, body text)"; // sql long 도 integer로 저장함
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP table memoList";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(MemoBean memo) {//db.insert가 long을 반환 하기 떄문에 long으로 만들어 준듯 함
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("head", memo.getMemo_head());
        value.put("body", memo.getMemo_body());
        return db.insert("memoList", null, value);
    }

    //
    public int update(MemoBean memo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("head", memo.getMemo_head());
        value.put("body", memo.getMemo_body());
        String id = String.valueOf(memo.getSequenceNumber());
//        return db.update("memos", "values", "id=?", new String[] {id});  // values뭐지?
        return 1 ;

    }
    //
    //
    public MemoBean get(int id){
        SQLiteDatabase db = getReadableDatabase();
        String idStr = String.valueOf(id);
        Cursor cursor = db.query("memos", null, "id=?", new String[]{idStr},
                null,null,null);
        if(cursor.moveToNext()){
            MemoBean memo = new MemoBean();
            memo.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            memo.setMemo_head(cursor.getString(cursor.getColumnIndex("head")));
            memo.setMemo_body(cursor.getString(cursor.getColumnIndex("body")));
            return memo;
        }else{
            return null;
        }
    }

    //
    public ArrayList<MemoBean> getAll() {
        SQLiteDatabase db = getReadableDatabase();// select는 read동작임
        Cursor cursor = db.query("memoList", null, null, null,
                null, null, null); //이렇게 쓰는건 sqlite만 그럼
        result.clear();
        result = new ArrayList<>();
        while (cursor.moveToNext()) { // moveToNext 커서가 다음꺼 확인하고 없으면 false 있으면 true
            MemoBean memo = new MemoBean();
            memo.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            memo.setMemo_head(cursor.getString(cursor.getColumnIndex("head")));
            memo.setMemo_body(cursor.getString(cursor.getColumnIndex("body")));

            result.add(memo);
        }
        return result;
    }


    //
//        public int delete(){
//        return;
//        }
    //

    public void clear(int item_sequence) {
        SQLiteDatabase db = getWritableDatabase();
        String form = String.format("DELETE FROM %s WHERE %s = %d", "memoList", "sequenceNumber", item_sequence);
        db.execSQL(form);
//        SQLiteDatabase db = getWritableDatabase();
//        String sequence = String.valueOf(item_sequence);
//        Log.i("check",String.valueOf(item_sequence));
//        return db.delete("memoList", "id=" +item_sequence, null);
    }
}
