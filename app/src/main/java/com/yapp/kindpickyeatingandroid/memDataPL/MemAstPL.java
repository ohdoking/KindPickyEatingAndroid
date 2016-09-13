package com.yapp.kindpickyeatingandroid.memDataPL;


import android.os.AsyncTask;

import com.yapp.kindpickyeatingandroid.dto.MemDataPL;

/**
 * Created by user on 2016-08-19.
 */
public class MemAstPL extends AsyncTask<MemDataPL, Void, String> {
    @Override
    protected String doInBackground(MemDataPL... params) {

        MemPLAPIClient c = new MemPLAPIClient();
        String s = c.getMemPLClient(params[0]);
        return s;
    }
}