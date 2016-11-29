package com.yapp.kindpickyeatingandroid.dto;

/**
 * Created by mac004 on 2016. 11. 29..
 */
public class InstagramHashTagResultImageItem {

    InstagramImageInfo low_resolution;
    InstagramImageInfo thumbnail;
    InstagramImageInfo standard_resolution;

    public InstagramImageInfo getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(InstagramImageInfo low_resolution) {
        this.low_resolution = low_resolution;
    }

    public InstagramImageInfo getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(InstagramImageInfo thumbnail) {
        this.thumbnail = thumbnail;
    }

    public InstagramImageInfo getStandard_resolution() {
        return standard_resolution;
    }

    public void setStandard_resolution(InstagramImageInfo standard_resolution) {
        this.standard_resolution = standard_resolution;
    }
}
