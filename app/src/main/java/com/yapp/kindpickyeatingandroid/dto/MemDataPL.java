package com.yapp.kindpickyeatingandroid.dto;

/**
 * Created by user on 2016-08-19.
 */
public class MemDataPL {
    String eml;
    String nm;
    String pwd;
    int vegeS;//6비건,락토,오보,락토-오보,페스코,폴로.
    int relS;//3무슬림 힌두교 사찰음식
    int error;
    String rg;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public MemDataPL(){

    }

    public int getVegeS() {
        return vegeS;
    }

    public void setVegeS(int vegeS) {
        this.vegeS = vegeS;
    }

    public int getRelS() {
        return relS;
    }

    public void setRelS(int relS) {
        this.relS = relS;
    }



    public String getEml() {
        return eml;
    }

    public void setEml(String eml) {
        this.eml = eml;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
