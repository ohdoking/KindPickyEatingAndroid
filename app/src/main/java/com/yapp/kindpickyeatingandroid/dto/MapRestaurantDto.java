package com.yapp.kindpickyeatingandroid.dto;

/**
 * Created by user on 2016-08-17.
 */
public class MapRestaurantDto {
    long id, grade;
    String name, thumb, address;
    double latatitue, longitute;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
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


