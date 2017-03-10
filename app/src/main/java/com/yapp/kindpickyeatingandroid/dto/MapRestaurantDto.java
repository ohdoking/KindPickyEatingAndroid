package com.yapp.kindpickyeatingandroid.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2016-08-17.
 */
public class MapRestaurantDto {

    long id;

    @SerializedName("category_id")
    @Expose
    long categoryId;

    String name, thumb, address;
    double latatitue, longitute;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public double getLatatitue() {
        return latatitue;
    }

    public void setLatatitue(double latatitue) {
        this.latatitue = latatitue;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }


}


