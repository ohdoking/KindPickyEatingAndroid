package com.yapp.kindpickyeatingandroid.dto;

import java.util.List;

/**
 * Created by mac004 on 2016. 11. 30..
 */
public class InstagramHashTagResult {
//    private String pagination;
//    private String meta;
    private List<InstagramHashTagResultItem> data;

    public List<InstagramHashTagResultItem> getData() {
        return data;
    }

    public void setData(List<InstagramHashTagResultItem> data) {
        this.data = data;
    }

//    public String getPagination() {
//        return pagination;
//    }
//
//    public void setPagination(String pagination) {
//        this.pagination = pagination;
//    }
//
//    public String getMeta() {
//        return meta;
//    }
//
//    public void setMeta(String meta) {
//        this.meta = meta;
//    }
}
