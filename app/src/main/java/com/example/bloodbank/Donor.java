package com.example.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Donor extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public Donor(Context context) {
        super(context, "data2.db", null, 1);
        sqLiteDatabase=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table donor(Name text, Address text, Mobile text, BloodGroup text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saved(String name, String add, String mob, String blood) {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Address", add);
        contentValues.put("Mobile", mob);
        contentValues.put("BloodGroup", blood);
        sqLiteDatabase.insert("donor",null,contentValues);
    }

    public Cursor getListContents() {
        sqLiteDatabase=this.getWritableDatabase();
        Cursor data=sqLiteDatabase.rawQuery("SELECT * FROM donor order by BloodGroup",null);
        return data;
    }
//    public ArrayList getcontents(String bld){
//        sqLiteDatabase= this.getReadableDatabase();
//        ArrayList<String> list= new ArrayList<String>();
//        Cursor c;
//
//        c= sqLiteDatabase.query("donor", null,"BloodGroup=?",new String[]{bld}, null, null, null);
//
//        if(c.moveToFirst()){
//            do{
//                list.add("Name - "+c.getString(c.getColumnIndex("Name")))
//            }
//        }
//    }
}
