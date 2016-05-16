package com.qianfeng.day14.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/5/12.
 */
public class DBAccess{
    private static final String TABLE_NAME = "user";

    private final MySqliteHelper helper;

    public DBAccess(Context context){
        helper = new MySqliteHelper(context);
    }

    /**
     *
     * @param user
     * @return  the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(User user){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("phone", user.getPhone());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    /**
     *
     * @param name
     * @return the number of rows affected if a whereClause is passed in, 0 otherwise.
     * To remove all rows and get a count pass "1" as the whereClause.
     */
    public int deleteByName(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, "name like ?", new String[]{"%" + name + "%"});
        db.close();
        return rows;
    }

    public Cursor queryAll(){
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.query(true, TABLE_NAME, null, null, null, null, null, null, null);
    }
}
