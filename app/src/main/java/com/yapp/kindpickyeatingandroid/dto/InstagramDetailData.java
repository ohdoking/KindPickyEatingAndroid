package com.yapp.kindpickyeatingandroid.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mac004 on 2016. 11. 30..
 */
@SuppressWarnings("serial")
public class InstagramDetailData implements Serializable {

    private String medium;
    private String thumbnail;
    private String big;
    private ArrayList<String> tags;
    private String author;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
