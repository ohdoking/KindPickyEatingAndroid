package com.yapp.kindpickyeatingandroid.service;

import com.yapp.kindpickyeatingandroid.dto.NaverSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ohdok on 2016-11-27.
 */
public interface KindPickyEatingNaverService {

    @GET("search/blog.json")
    Call<NaverSearchResult> getBlogInfo(@Query("query") String query);
}
