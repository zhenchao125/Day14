package com.qianfeng.day14_contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ContentResolver resolver;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();
        resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri uri2 = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);
        while(cursor.moveToNext()){
            User user = new User();
            users.add(user);
            int cantact_id = cursor.getInt(0);
            //            Log.d("MainActivity", "cantact_id:" + cantact_id);
            Cursor cursor2 = resolver.query(uri2, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{cantact_id + ""}, null);
            while(cursor2.moveToNext()){
                String mimeType = cursor2.getString(1);
                String a = cursor2.getString(0);
                if(mimeType.equals("vnd.android.cursor.item/email_v2")){
                    user.seteMail(a);
                }else if(mimeType.equals("vnd.android.cursor.item/phone_v2")){
                    user.setPhone(a);
                }else if(mimeType.equals("vnd.android.cursor.item/name")){
                    user.setName(a);
                }
            }
        }

        Log.d("MainActivity", "users:" + users);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount(){
            return users.size();
        }

        @Override
        public User getItem(int position){
            return users.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent){
            v = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = (TextView) v;
            textView.setText(getItem(position).geteMail());
            return v;
        }
    }
}
