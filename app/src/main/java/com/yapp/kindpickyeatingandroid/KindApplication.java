package com.yapp.kindpickyeatingandroid;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by KwonYeJin on 2016. 8. 20..
 */
public class KindApplication extends Application{
        @Override public void onCreate() {
            super.onCreate();
            Dexter.initialize(this);


        }
}
