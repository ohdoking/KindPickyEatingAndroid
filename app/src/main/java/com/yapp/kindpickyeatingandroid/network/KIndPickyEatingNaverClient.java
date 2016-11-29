package com.yapp.kindpickyeatingandroid.network;

import android.content.Context;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.service.KindPickyEatingNaverService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ohdok on 2016-11-27.
 */
public class KindPickyEatingNaverClient implements KindPickyEatingClient {

    private Context context;
    private String BASE_URL;
    private KindPickyEatingNaverService apiService;
    private String clientKey;
    private String secretKey;

    public KindPickyEatingNaverClient(Context context){
        this.context = context;
        init();
    }
    public void init(){
        BASE_URL = context.getResources().getString(R.string.naver_api_url);
        clientKey = context.getResources().getString(R.string.naver_client_key);
        secretKey = context.getResources().getString(R.string.naver_secret_key);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-Naver-Client-Id", clientKey)
                        .header("X-Naver-Client-Secret", secretKey)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(KindPickyEatingNaverService.class);
    }

    public KindPickyEatingNaverService getKindPickyEactingService(){
        return apiService;
    }
}
