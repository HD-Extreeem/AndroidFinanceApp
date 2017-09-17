package com.example.hadideknache.financeapp;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class Income {
    private int id;
    private String category;
    private String time;
    private String date;
    private String earn;

    public Income(int  id, String category,String time,String date,String earn){
        this.id=id;
        this.category = category;
        this.time=time;
        this.date=date;
        this.earn=earn;
    }
    public String getCost() {
        return earn;
    }

    public void setCost(String cost) {
        this.earn = cost;
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
}
