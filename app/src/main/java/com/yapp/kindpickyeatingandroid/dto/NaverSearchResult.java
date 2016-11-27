package com.yapp.kindpickyeatingandroid.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohdok on 2016-11-27.
 */
public class NaverSearchResult {

    private Integer total;
    private Integer start;
    private Integer display;
    private List<NaverSearchResultItem> items;

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

    public List<NaverSearchResultItem> getItems() {
        return items;
    }

    public void setItems(List<NaverSearchResultItem> item) {
        this.items = item;
    }
}
