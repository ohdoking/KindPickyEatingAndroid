package com.yapp.kindpickyeatingandroid.font;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Attr;

/**
 * Created by yeeun on 2017-02-08.
 * NotoSans_Regular 폰트 설정
 */

public class Noto_Regul extends TextView {

    public Noto_Regul(Context context) {
        super(context);
        setType(context);
    }
    public Noto_Regul(Context con, AttributeSet attr){
        super(con,attr);
        setType(con);
    }

    public Noto_Regul(Context con, AttributeSet attr, int defStyleAttr){
        super(con,attr,defStyleAttr);
        setType(con);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Noto_Regul(Context con, AttributeSet attr, int defStyleAttr, int defStyleRes){
        super(con,attr,defStyleAttr,defStyleRes);
        setType(con);
    }

    private void setType(Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"NotoSansKR-Regular.otf"));

    }

}
