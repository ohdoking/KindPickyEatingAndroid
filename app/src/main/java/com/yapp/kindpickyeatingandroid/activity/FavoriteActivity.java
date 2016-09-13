package com.yapp.kindpickyeatingandroid.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.Adapter;
import com.yapp.kindpickyeatingandroid.dto.Item;

import java.util.ArrayList;

public class FavoriteActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ListView listView=(ListView)findViewById(R.id.listview);

        ArrayList<Item> data=new ArrayList<>();
        Item like1=new Item(R.drawable.logo,"PASHA", 4.5f, "서초구 서초동 희망빌딩 4층");
        Item like2=new Item(R.drawable.logo,"모글",4.0f, "종로구 지봉로 18");
        Item like3=new Item(R.drawable.logo,"기억밥", 2.5f, "서초구 서초동 희망빌딩 4층");
        Item like4=new Item(R.drawable.logo,"맘마",1.0f, "종로구 화명동");
        Item like5=new Item(R.drawable.logo,"가솝밥", 3.5f, "서초구 서초동 희망빌딩 4층");
        Item like6=new Item(R.drawable.logo,"냠냠",2.0f, "종로구 지봉로 18");
        Item like7=new Item(R.drawable.logo,"PASHA", 2.5f, "서초구 서초동 희망빌딩 4층");
        Item like8=new Item(R.drawable.logo,"모글",4.0f, "종로구 지봉로 18");
        Item like9=new Item(R.drawable.logo,"PASHA", 4.5f, "서초구 서초동 희망빌딩 4층");
        Item like10=new Item(R.drawable.logo,"모글",4.0f, "종로구 지봉로 18");

        data.add(like1);
        data.add(like2);
        data.add(like3);
        data.add(like4);
        data.add(like5);
        data.add(like6);
        data.add(like7);
        data.add(like8);
        data.add(like9);
        data.add(like10);



        Adapter adapter=new Adapter(this,R.layout.activity_item,data);
        listView.setAdapter(adapter);
    }
}
