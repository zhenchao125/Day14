package com.qianfeng.day14_second;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity{
    /*
      android list avd  查看当前设备上可用的虚拟机

      emultor @虚拟机名字
     */
    private ContentResolver resolver;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取ContentResolver对象，负责向ContentProvider来请求数据
        resolver = getContentResolver();

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                );
        listView.setAdapter(adapter);
    }

    public void query(View view){
        Uri uri = Uri.parse("content://xxx/person");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        adapter.changeCursor(cursor);

    }

    public void insert(View view){
        Uri uri = Uri.parse("content://xxx");
        ContentValues values = new ContentValues();
        values.put("name", "四赵");
        values.put("age", 30);
        values.put("phone", 110);
        resolver.insert(uri, values);
    }

    public void update(View view){
        Uri uri = Uri.parse("content://xxx");
        ContentValues values = new ContentValues();
        values.put("name", "赵8");
        resolver.update(uri, values, "name like ?", new String[]{"%" + "七" + "%"});
    }

    public void delete(View view){
        Uri uri = Uri.parse("content://xxx");  // 人赵
        resolver.delete(uri, "name like ?", new String[]{"%赵%"});
    }
}
