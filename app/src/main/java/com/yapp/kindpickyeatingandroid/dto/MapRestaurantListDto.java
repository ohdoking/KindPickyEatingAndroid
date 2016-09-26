package com.yapp.kindpickyeatingandroid.dto;

import java.util.List;

/**
 * Created by user on 2016-09-25.
 */
public class MapRestaurantListDto {
    public String state;
    public List<MapRestaurantDto> list;

    public List<MapRestaurantDto> getList() {
        return list;
    }

    public void setList(List<MapRestaurantDto> list) {
        this.list = list;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
