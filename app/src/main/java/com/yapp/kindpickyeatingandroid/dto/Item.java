package com.yapp.kindpickyeatingandroid.dto;

//데이터를 처리하는 클래스(Listviewitem)
public class Item {
    private int icon;
    private String name;
    private float grade;
    private String address;

    public int getIcon(){return icon;}
    public String getName(){return name;}
    public float getGrade() {return grade;}
    public String getAddress() {return address;}

    public Item(int icon, String name, float grade, String address){
        this.icon=icon;
        this.name=name;
        this.grade=grade;
        this.address=address;
    }
}
