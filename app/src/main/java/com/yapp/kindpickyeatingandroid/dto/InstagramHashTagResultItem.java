package com.yapp.kindpickyeatingandroid.dto;

import java.util.List;

/**
 * Created by mac004 on 2016. 11. 29..
 */
public class InstagramHashTagResultItem {

    private String id;
    private String type;
    private List<String> users_in_photo;
    private String filter;
    private List<String> tags;
    private String comments;
    private String caption;
    private String likes;
    private String link;
    private String user;
    private String created_time;
    private InstagramHashTagResultImageItem images;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUsers_in_photo() {
        return users_in_photo;
    }

    public void setUsers_in_photo(List<String> users_in_photo) {
        this.users_in_photo = users_in_photo;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public InstagramHashTagResultImageItem getImages() {
        return images;
    }

    public void setImages(InstagramHashTagResultImageItem images) {
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
