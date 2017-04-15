package com.yapp.kindpickyeatingandroid.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by yeeun on 2017-03-11.
 */

public class BaseAppCompatActivity extends AppCompatActivity{
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
