package com.example.liudongxun.myjianshu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liudongxun on 2017/09/07.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {


    public   MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
     super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         String sql="create table Book("+"id integer primary key autoincrement,"+"url)";
         db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }
}
