package com.yapp.kindpickyeatingandroid.memDataAccess;

import android.util.Log;


import com.yapp.kindpickyeatingandroid.dto.MemData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016-08-19.
 */
public class MemberAPIClient {
    final static  String urlStr="http://52.196.59.156:8080/kindpickyeating/user/login";


    public MemData getMemClient(String id, String pwd){
        MemData m=new MemData();
        try{
            String body="email="+id+"&pw="+pwd;
            URL url=new URL(urlStr);

            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            //서버에 데이터 입력
            OutputStream os=http.getOutputStream();
            os.write(body.getBytes("euc-kr"));
            os.flush();
            os.close();
            Log.i("ohdoking : ",http.getResponseCode()+http.getResponseMessage());
            //서버에서 받아오기
            InputStream in=new BufferedInputStream(http.getInputStream());
            JSONObject json=new JSONObject(getStringFromInputStream(in));
            m=parseJSON(json);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return m;
    }

    private MemData parseJSON(JSONObject json) throws JSONException {

        MemData m=new MemData();
        m.setId(json.getString("email"));
        m.setRel(json.getString("religion"));
        m.setNick(json.getString("nickname"));
        m.setUser(json.getString("userCategory"));
        return m;
    }

    private static String getStringFromInputStream(InputStream is){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {

                try {

                    br.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
