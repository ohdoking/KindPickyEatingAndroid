package com.yapp.kindpickyeatingandroid.network;

import android.content.Context;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingInstagramService;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingServerService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac004 on 2016. 11. 29..
 */
public class KindPickyEatingInstagramClient implements KindPickyEatingClient{
    private Context context;
    private String BASE_URL;
    private String AUTHKEY;
    private KindPickyEatingInstagramService apiService;

    public KindPickyEatingInstagramClient(Context context){
        this.context = context;
        init();
    }

    public void init(){
        BASE_URL = context.getResources().getString(R.string.instagram_url);
        AUTHKEY = context.getResources().getString(R.string.instagram_key);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
            new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter("access_token",AUTHKEY).build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }
            })
        .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(KindPickyEatingInstagramService.class);

    }

    public KindPickyEatingInstagramService getKindPickyEactingService(){
        return apiService;
    }
}
