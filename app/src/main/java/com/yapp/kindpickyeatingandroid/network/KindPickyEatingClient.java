package com.yapp.kindpickyeatingandroid.network;

import com.yapp.kindpickyeatingandroid.service.KindPickyEactingService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ohdok on 2016-09-16.
 */
public class KindPickyEatingClient {

    private static final String BASE_URL = "http://52.196.59.156:8080/kindpickyeating/";
    private KindPickyEactingService apiService;

    public KindPickyEatingClient(){

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
