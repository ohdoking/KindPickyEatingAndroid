package com.yapp.kindpickyeatingandroid;

import android.app.Application;

import com.karumi.dexter.Dexter;
import com.tsengvn.typekit.Typekit;

/**
 * Created by KwonYeJin on 2016. 8. 20..
 */
public class KindApplication extends Application{
        @Override public void onCreate() {
            super.onCreate();
            Dexter.initialize(this);
            Typekit.getInstance()
                    .addNormal(Typekit.createFromAsset(this, "NotoSansKR-Medium-Hestia.otf"))
                    .addBold(Typekit.createFromAsset(this, "NotoSansKR-Bold-Hestia.otf"));


        }
}
