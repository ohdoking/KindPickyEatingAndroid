package com.yapp.kindpickyeatingandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.common.KindPickyEatingConstant;
import com.yapp.kindpickyeatingandroid.dto.InstagramDetailData;

public class InstagramDetailActivity extends BaseAppCompatActivity {
    private ImageView instaPicture;
    private TextView instaId;
    private ImageView instaApp;
    private TextView instaTag;
    private String tag="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_detail);

        Intent intent = getIntent();
        InstagramDetailData instagramDetailData = (InstagramDetailData) intent.getSerializableExtra(KindPickyEatingConstant.INSTADETAILDATA);
        Log.i("ohdoking22",instagramDetailData.getAuthor());

        instaPicture = (ImageView)findViewById(R.id.instaPicture);
//        instaPicture.setImageResource(R.drawable.scenery);
        Glide.with(getApplicationContext()).load(instagramDetailData.getMedium()).into(instaPicture);

        instaId = (TextView)findViewById(R.id.instaId);
        instaId.setText(instagramDetailData.getAuthor().replace("\"",""));

        instaApp = (ImageView)findViewById(R.id.instaApp);
        instaApp.setImageResource(R.drawable.bedge);


        instaTag = (TextView)findViewById(R.id.instaTag);
        for(int i = 0; i < instagramDetailData.getTags().size(); i++) {
            tag = tag + ("#" + instagramDetailData.getTags().get(i) + " ");
        }
        instaTag.setText(tag);
    }

}
