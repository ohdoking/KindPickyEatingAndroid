package com.yapp.kindpickyeatingandroid.service;

import com.yapp.kindpickyeatingandroid.dto.InstagramHashTagResultItem;
import com.yapp.kindpickyeatingandroid.dto.NaverSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mac004 on 2016. 11. 29..
 */
public interface KindPickyEatingInstagramService {

//    @GET("media/{media-id}")
//    Call<InstagramHashTagResultItem> searchImage(@Path("media-id") String id);
//
//    @GET("tags/{tag-name}")
//    Call<NaverSearchResult> searchHashtag(@Path("tag-name") String tagName);

    @GET("tags/{tag-name}/media/recent")
    Call<InstagramHashTagResultItem> searchHashtagRecent(@Path("tag-name") String tagName);
}
