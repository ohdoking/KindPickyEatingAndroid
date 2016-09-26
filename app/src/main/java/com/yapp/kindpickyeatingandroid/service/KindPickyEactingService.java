package com.yapp.kindpickyeatingandroid.service;

import com.yapp.kindpickyeatingandroid.dto.JoinDto;
import com.yapp.kindpickyeatingandroid.dto.MapRestaurantListDto;
import com.yapp.kindpickyeatingandroid.dto.RestaurantDetailDto;
import com.yapp.kindpickyeatingandroid.dto.ResultStateDto;
import com.yapp.kindpickyeatingandroid.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ohdok on 2016-09-15.
 */
public interface KindPickyEactingService {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserDto> login(@Field("email") String id, @Field("pw") String password);

    @POST("user/join")
    Call<ResultStateDto> join(@Body JoinDto joinDto);

    @GET("restaurant/list")
    Call<MapRestaurantListDto> map(@Query("latitute") String lat, @Query("longitute") String lon);

    @GET("restaurant/detail/{id}")
    Call<RestaurantDetailDto> restaurantDetailInfo(@Path("id") Long id);


}
