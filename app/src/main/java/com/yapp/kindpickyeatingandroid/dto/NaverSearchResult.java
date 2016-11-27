package com.yapp.kindpickyeatingandroid.dto;

import java.util.ArrayList;

/**
 * Created by ohdok on 2016-11-27.
 */
public class NaverSearchResult {

    private Integer total;
    private Integer start;
    private Integer display;
    private ArrayList<NaverSearchResultItem> item;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public ArrayList<NaverSearchResultItem> getItem() {
        return item;
    }

    public void setItem(ArrayList<NaverSearchResultItem> item) {
        this.item = item;
    }
}
