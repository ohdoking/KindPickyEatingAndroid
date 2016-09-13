package com.yapp.kindpickyeatingandroid.memDataAccess;


import android.os.AsyncTask;

import com.yapp.kindpickyeatingandroid.dto.MemData;

/**
 * Created by user on 2016-08-19.
 */
public class MemAst extends AsyncTask<String, Void, MemData> {
    @Override
    protected MemData doInBackground(String... params) {
        MemberAPIClient c = new MemberAPIClient();

        String id = params[0];
        String pwd=params[1];
        MemData m= c.getMemClient(id,pwd);
        return m;


    }
}
