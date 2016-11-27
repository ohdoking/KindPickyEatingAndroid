package com.yapp.kindpickyeatingandroid.network;

import android.content.Context;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.service.KindPickyEactingService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ohdok on 2016-09-16.
 */
public class KindPickyEatingServerClient implements KindPickyEatingClient {

    private Context context;
    private String BASE_URL;
    private KindPickyEactingService apiService;

    public KindPickyEatingServerClient(Context context){
        this.context = context;
        init();
   }

    public void init(){
        BASE_URL = context.getResources().getString(R.string.aws_api_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(KindPickyEactingService.class);

    }

    public KindPickyEactingService getKindPickyEactingService(){
        return apiService;
    }
}
