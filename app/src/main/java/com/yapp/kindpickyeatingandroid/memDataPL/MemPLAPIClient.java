package com.yapp.kindpickyeatingandroid.memDataPL;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yapp.kindpickyeatingandroid.dto.MemDataPL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016-08-19.
 */
public class MemPLAPIClient {
    final static  String urlStr="http://52.196.59.156:8080/kindpickyeating/user/join";



    public String getMemPLClient(MemDataPL memPL){

        String s = "";
        try{
//            String body="email="+memPL.getEml()+"&password="+memPL.getPwd()+"&nickname="+memPL.getNm()+"&religion="+memPL.getRelS()+"&user_category="+memPL.getVegeS();
            String body="email=test11111@naver.com&password=1234&nickname=v&religion=7&user_category=1";
            URL url=new URL(urlStr);
           HttpURLConnection http=(HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setRequestProperty("Content-Type","application/json");

            JSONObject jsonObj = new JSONObject();
            try {
                jsonObj.put("email", memPL.getEml());
                jsonObj.put("password", memPL.getPwd());
                jsonObj.put("nickname", memPL.getNm());
                jsonObj.put("religion", memPL.getRelS());
                jsonObj.put("user_category", memPL.getVegeS());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //서버에 데이터 입력

            OutputStreamWriter wr = new OutputStreamWriter(http.getOutputStream());
            wr.write(jsonObj.toString());
            wr.flush();


//
//            OutputStream os=http.getOutputStream();
//            os.write(memPL.toString());
//            os.flush();
//            os.close();
            Log.i("ohdoking : ",http.getResponseCode()+http.getResponseMessage());
            //////////////
            memPL.setError(http.getResponseCode());

            //서버에서 받아오기
            InputStream in=new BufferedInputStream(http.getInputStream());
            JSONObject json=new JSONObject(getStringFromInputStream(in));
            s=parseJSON(json);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return s;
    }

    private String parseJSON(JSONObject json) throws JSONException {

        String s="";
        s=json.getString("state");
        return s;
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
