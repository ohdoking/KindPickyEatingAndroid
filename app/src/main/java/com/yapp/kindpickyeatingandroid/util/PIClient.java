package com.yapp.kindpickyeatingandroid.util;

import com.yapp.kindpickyeatingandroid.dto.MapRestaurantDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 2016-08-17.
 */
public class PIClient {
    final static String urlString = "http://52.196.59.156:8080/kindpickyeating/restaurant/list";

    public ArrayList<MapRestaurantDto> getPIClient(String lat, String lon) {
        ArrayList<MapRestaurantDto> sampleList = new ArrayList<MapRestaurantDto> ();

        try {

            String body = "?latitute="+lat+"&longitute="+lon;
            String tempString = urlString+body; //+"?title="+title;
            URL url = new URL(tempString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); //안드로이드에서 HTTP 호출을 하기 위해서는 HttpURLConnection이라는 클래스를 사용

//            urlConnection.setRequestMethod("GET");
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(true);


            // URL이라는 클래스에 API를 호출하고자 하는 url주소를 지정하여 생성한후, url.openConnection()을 이용해서, HTTP Connection을 열고 호출

            //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//요청헤더설정
            //urlConnection.setRequestProperty("Connection", "Keep-Alive"); //?
            //OutputStream os = urlConnection.getOutputStream();
//            os.write(body.getBytes("EUC-KR"));
//            os.flush();
//            os.close();

            //BufferedReader br = new BufferedReader(new OutputStreamReader(urlConnection.getInputStream(),"EUC-KR"),urlConnection.getContentLength());
            //String buf;

            //리턴되어 오는 문자열을 읽어서 JSON형태로 파싱
            //먼저 위와 같이 urlConnection으로 부터 getInputStream() 메서드를 통해서
            //InputStream을 리턴받고 getStringFromInput(InputStream xx)이라는 메서드를 이용하여
            // inputStream을 String으로 변환한후에 JSONObject로 변환한다. (여기서 getStringFromInput은
            // 미리 정해진 메서드가 아니고 위의 소스코드 처럼 InputStream 을 String으로 변환하기 위해서 여기서 지정된 코드들이다.)

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            JSONArray jsonArray = json.getJSONArray("list");
            for (int i = 0 ; i < jsonArray.length(); i++ ){
                MapRestaurantDto temp = new MapRestaurantDto();
                temp = parseJSON(jsonArray.getJSONObject(i));//json에서 샘플로 변환
                sampleList.add(temp);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sampleList; //받아온 데이터를 가공하여 문자열로 리턴함
    }

    //JSON Parser
    private MapRestaurantDto parseJSON(JSONObject j) throws JSONException {

        MapRestaurantDto s = new MapRestaurantDto();
        s.setId(j.getLong("id"));
        s.setCategoryId(j.getLong("category_id"));
        s.setName(j.getString("name"));
        s.setThumb(j.getString("thumb"));
        s.setAddress(j.getString("address"));
        s.setLatatitue(j.getDouble("latatitue"));
        s.setLongitute(j.getDouble("longitute"));

        return s;
    }
    //미리 정해진 메서드가 아니고 위의 소스코드 처럼 InputStream 을 String으로 변환하기 위해서 여기서 지정된 코드
    private static String getStringFromInputStream(InputStream is) {

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





