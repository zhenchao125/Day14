package com.qianfeng.day14.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MySqliteHelper extends SQLiteOpenHelper{

    public MySqliteHelper(Context context){
        super(context, "1604.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //建表的操作
        String sql = "create table user(_id integer primary key autoincrement, name text, age integer, phone text)";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        for(int i = 0; i < 20; i++){
            values.put("name", "张三" + i);
            values.put("age", 15);
            values.put("phone", "1383838438" + i);
            db.insert("user", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
