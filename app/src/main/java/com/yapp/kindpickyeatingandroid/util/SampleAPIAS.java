package com.yapp.kindpickyeatingandroid.util;

import android.os.AsyncTask;

import com.yapp.kindpickyeatingandroid.dto.MapRestaurantDto;

import java.util.ArrayList;

/**
 * Created by user on 2016-08-17.
 */
public class SampleAPIAS extends AsyncTask<String, Void, ArrayList<MapRestaurantDto> > {
    //비동기 처리
    @Override
    protected ArrayList<MapRestaurantDto> doInBackground(String... params) {
        PIClient c = new PIClient();

        String mylat = params[0];
        String mylon = params[1];
        ArrayList<MapRestaurantDto>  s = c.getPIClient(mylat,mylon);
        return s;


    }
}
