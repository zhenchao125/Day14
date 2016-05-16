package com.qianfeng.day14_sms;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity{

    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Uri uri = Uri.parse("content://sms");
        resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "body", "date"}, null, null, null);
        ListView listView = (ListView) findViewById(R.id.listView);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.sms_item,
                cursor,
                new String[]{"address", "body"},
                new int[]{R.id.address, R.id.body}
               /* CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER*/
        );
        //将来一旦观察的那个Uri对应的数据发生变化，则会回调内容观察者中的onChange方法
        resolver.registerContentObserver(uri, true, new ContentObserver(new Handler()){
            @Override
            public void onChange(boolean selfChange){
                Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "body", "date"}, null, null, null);
                adapter.changeCursor(cursor);
            }
        });

        listView.setAdapter(adapter);

    }

    public void insert(View view){
        Uri uri = Uri.parse("content://sms/inbox");
        ContentValues values = new ContentValues();
        values.put("address", "95588");
        values.put("body", "尊敬的工商银行客户，你尾号为888的卡收入(工资)358,960.00元，请放心消费");
        values.put("date", System.currentTimeMillis());
        resolver.insert(uri, values);

    }
}
