package com.yapp.kindpickyeatingandroid.activity;

import android.app.Activity;
import android.content.Context;
import com.tsengvn.typekit.TypekitContextWrapper;
/**
 * Created by yeeun on 2017-03-11.
 */

public class BaseActivity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
