package com.example.hadideknache.financeapp;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class Expenditure {
    private int id;
    private String category;
    private String time;
    private String date;
    private String cost;
    private String title;

    public Expenditure(int  id, String category,String time,String date,String cost,String title){
        this.id=id;
        this.category = category;
        this.time=time;
        this.date=date;
        this.cost=cost;
        this.title=title;
    }

    public Expenditure(String category, String time, String date, String cost,String title) {
        this.category = category;
        this.time=time;
        this.date=date;
        this.cost=cost;
        this.title=title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
}

