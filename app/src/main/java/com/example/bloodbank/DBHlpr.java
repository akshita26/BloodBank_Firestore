package com.example.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHlpr extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public DBHlpr(Context context) {
        super(context, "data.db", null, 1);
        sqLiteDatabase=getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table people(Name text, Address text, Date text, Mobile text, BloodGroup text, LastDate text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savedata(String s1, String s2, String s3, String s4, String s5, String s6){
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",s1);
        contentValues.put("Address", s2);
        contentValues.put("Date", s3);
        contentValues.put("Mobile", s4);
        contentValues.put("BloodGroup", s5);
        contentValues.put("LastDate", s6);
        sqLiteDatabase.insert("people",null,contentValues);

    }
    public boolean checkpd(String s){
        Cursor c;
        c=sqLiteDatabase.query("people",null,"Mobile=?",new String[]{s},null,null,null);
        c.moveToFirst();
        if(c.getCount()<1)
            return false;
        else
            return true;
    }


}
