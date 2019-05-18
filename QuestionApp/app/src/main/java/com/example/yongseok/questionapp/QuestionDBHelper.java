package com.example.yongseok.questionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class QuestionDBHelper extends SQLiteOpenHelper {
    private ArrayList<QuestionBean> result;

    public QuestionDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {//앱버전과 디비 버전이 다름
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table questionList (sequenceNumber integer primary key autoincrement, problem,scoring,answer1,answer2,answer3,answer4,type,answerNum,yyDate, ttTime)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP table questionList";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(QuestionBean question) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("problem", question.getProblem());
        value.put("scoring", question.getScoring());
        value.put("answer1", question.getAnswer1());
        value.put("answer2", question.getAnswer2());
        value.put("answer3", question.getAnswer3());
        value.put("answer4", question.getAnswer4());
        value.put("type", question.getType());
        value.put("answerNum", question.getAnswerNum());
        //date 추가
        value.put("yyDate", question.getYyDate());
        value.put("ttTime", question.getTtTime());

        return db.insert("questionList", null, value);
    }

    public QuestionBean get(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String idStr = String.valueOf(id);
        Cursor cursor = db.query("questionList", null, "id=?", new String[]{idStr}, null, null, null);
        if(cursor.moveToNext()){
            QuestionBean question = new QuestionBean();
            question.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            question.setProblem(cursor.getString(cursor.getColumnIndex("problem")));
            question.setScoring(cursor.getString(cursor.getColumnIndex("scoring")));
            question.setAnswer1(cursor.getString(cursor.getColumnIndex("answer1")));
            question.setAnswer2(cursor.getString(cursor.getColumnIndex("answer2")));
            question.setAnswer3(cursor.getString(cursor.getColumnIndex("answer3")));
            question.setAnswer4(cursor.getString(cursor.getColumnIndex("answer4")));
            question.setType(cursor.getString(cursor.getColumnIndex("type")));
            question.setAnswerNum(cursor.getInt(cursor.getColumnIndex("answerNum")));
            question.setYyDate(cursor.getString(cursor.getColumnIndex("yyDate")));
            question.setTtTime(cursor.getString(cursor.getColumnIndex("ttTime")));

            return question;
        }else{
            return null;
        }
    }

    public ArrayList<QuestionBean> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("questionList", null, null, null, null, null, null);
        result = new ArrayList<>();
        while (cursor.moveToNext()) {
            QuestionBean question = new QuestionBean();
            question.setSequenceNumber(cursor.getInt(cursor.getColumnIndex("sequenceNumber")));
            question.setProblem(cursor.getString(cursor.getColumnIndex("problem")));
            question.setScoring(cursor.getString(cursor.getColumnIndex("scoring")));
            question.setAnswer1(cursor.getString(cursor.getColumnIndex("answer1")));
            question.setAnswer2(cursor.getString(cursor.getColumnIndex("answer2")));
            question.setAnswer3(cursor.getString(cursor.getColumnIndex("answer3")));
            question.setAnswer4(cursor.getString(cursor.getColumnIndex("answer4")));
            question.setType(cursor.getString(cursor.getColumnIndex("type")));
            question.setAnswerNum(cursor.getInt(cursor.getColumnIndex("answerNum")));
            question.setYyDate(cursor.getString(cursor.getColumnIndex("yyDate")));
            question.setTtTime(cursor.getString(cursor.getColumnIndex("ttTime")));

            result.add(question);
        }
        return result;
    }

    public int update(int item_sequence, QuestionBean question){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("problem", question.getProblem());
        value.put("scoring", question.getScoring());
        value.put("answer1", question.getAnswer1());
        value.put("answer2", question.getAnswer2());
        value.put("answer3", question.getAnswer3());
        value.put("answer4", question.getAnswer4());
        value.put("type", question.getType());
        value.put("answerNum", question.getAnswerNum());
        //date 추가
        value.put("yyDate", question.getYyDate());
        value.put("ttTime", question.getTtTime());

        String str = ""+item_sequence;
        return db.update("questionList", value, String.format("%s=?","sequenceNumber"), new String[]{str});
    }

    public void clear(int item_sequence){
        SQLiteDatabase db = getWritableDatabase();
        String form = String.format("DELETE FROM %s WHERE %s = %d", "questionList", "sequenceNumber",item_sequence);
        db.execSQL(form);
    }
}
