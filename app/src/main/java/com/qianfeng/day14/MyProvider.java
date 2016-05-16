package com.qianfeng.day14;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qianfeng.day14.db.MySqliteHelper;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MyProvider extends ContentProvider{

    private MySqliteHelper helper;
    private static UriMatcher matcher;
    private static int USER_MATCH = 0;
    private static int PERSON_MATCH = 1;

    static{
        //创建一个Uri匹配器
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        //想匹配器中添加Uri    参数三称之为：匹配码
        matcher.addURI("xxx", "user", USER_MATCH);  // content://xxx/user1
        matcher.addURI("xxx", "person", PERSON_MATCH);  // content://xxx/person
    }



    /**
     * 当ContentProvider创建时候回调这个方法
     * 也是在主线程中执行，不能执行耗时操作
     *
     * @return
     */
    @Override
    public boolean onCreate(){
        Log.d("MyProvider", "oncreate...");
        helper = new MySqliteHelper(getContext());
        return false;
    }

    /**
     * 查询操作
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Log.d("MyProvider", "query...");
        if(matcher.match(uri) == USER_MATCH){
            SQLiteDatabase db = helper.getReadableDatabase();
            return db.query(true, "user", projection, selection, selectionArgs, null, null, sortOrder, null);
        }else if(matcher.match(uri) == PERSON_MATCH){
            Log.d("MyProvider", "查询person");

        }else if(matcher.match(uri) == UriMatcher.NO_MATCH){
            throw new RuntimeException("你的Uri有很大的问题，强检查");
        }
        return null;
    }

    /**
     * 返回数据的类型
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri){
        return null;
    }

    /**
     * 插入
     *
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values){
        Log.d("MyProvider", "insert...");
        SQLiteDatabase db = helper.getReadableDatabase();
        db.insert("user", null, values);
        return uri;
    }

    /**
     * 删除
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        Log.d("MyProvider", "delete...");
        SQLiteDatabase db = helper.getReadableDatabase();
        int rows = db.delete("user", selection, selectionArgs);
        db.close();
        return rows;
    }

    /**
     * 更新
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        Log.d("MyProvider", "update...");
        SQLiteDatabase db = helper.getReadableDatabase();
        int rows = db.update("user", values,selection, selectionArgs);
        db.close();
        return rows;
    }
}
