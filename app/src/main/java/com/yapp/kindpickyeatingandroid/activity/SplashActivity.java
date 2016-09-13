package com.yapp.kindpickyeatingandroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.yapp.kindpickyeatingandroid.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME = 2000;

        ImageView img = (ImageView) findViewById(R.id.splashimg);
        Drawable resizeImg = resize(getResources().getDrawable(R.drawable.splash));
        img.setImageDrawable(resizeImg);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                overridePendingTransition(0,android.R.anim.fade_in);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        },SPLASH_TIME);
    }
    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 1440, 2560, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
}
