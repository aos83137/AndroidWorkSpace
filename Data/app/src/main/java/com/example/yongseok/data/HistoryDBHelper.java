package com.example.yongseok.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;


// 추가,삭제,업데이트 등 기능 추가하고싶으면 여기에다가 추가하면됨
public class HistoryDBHelper extends SQLiteOpenHelper {
    public HistoryDBHelper(@Nullable Context context, @Nullable  String name, @Nullable  SQLiteDatabase.CursorFactory factory, int version) {//앱버전과 디비 버전이 다름
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // db헬퍼 만들때 String name이 db네임임 이름이 있을수도있고 없을 수도있다. 없으면 여기onCreaete 파라메터값 변수가 이름이됨
        String sql = "create table history ( sequenceNumber integer primary key autoincrement, name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP table history";
        db.execSQL(sql);
        onCreate(db);
    }
    public long insert(UserBean user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues(); //이렇게 쓰는건 sqlite만 그럼
        value.put("name", user.getName()); //이렇게 쓰는건 sqlite만 그럼
        return db.insert("history", null, value);
    }

    public ArrayList<UserBean> getAll(){
        SQLiteDatabase db = getReadableDatabase();// select는 read동작임
        Cursor cursor = db.query("history", null, null, null,
                null, null, null); //이렇게 쓰는건 sqlite만 그럼
        ArrayList<UserBean> result = new ArrayList<>();
        while(cursor.moveToNext()){ // moveToNext 커서가 다음꺼 확인하고 없으면 false 있으면 true
            UserBean user = new UserBean();
            user.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            result.add(user);
        }
        return result;
    }

}
