package com.yapp.kindpickyeatingandroid.dto;

/**
 * Created by ohdok on 2016-09-17.
 */
public class JoinDto {

    String email;
    String nickname;
    String password;
    int userCategory;//6비건,락토,오보,락토-오보,페스코,폴로.
    int religion;//3무슬림 힌두교 사찰음식

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(int userCategory) {
        this.userCategory = userCategory;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }
}