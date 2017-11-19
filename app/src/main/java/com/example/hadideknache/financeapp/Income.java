package com.example.hadideknache.financeapp;

/**
 * This class is a pojo class handling the expenditures in a object
 * Created by hadideknache on 2017-09-17.
 */

public class Income {
    private int id;
    private String category;
    private String time;
    private String date;
    private String earn;
    private String title;

    /**
     * Constructor of income
     * @param id the id given for the income
     * @param category the category of the item
     * @param time the time when earned
     * @param date the date when earned
     * @param earn the money earned
     * @param title the name of the object
     */
    public Income(int  id, String category,String time,String date,String earn,String title){
        this.id=id;
        this.category = category;
        this.time=time;
        this.date=date;
        this.earn=earn;
        this.title=title;
    }

    /**
     * Constructor of income
     * @param category the category of the item
     * @param time the time when earned
     * @param date the date when earned
     * @param earn the money earned
     * @param title the name of the object
     */
    public Income(String category, String time, String date, String earn,String title) {
        this.category = category;
        this.time=time;
        this.date=date;
        this.earn=earn;
        this.title=title;
    }

    //Setters
    public void setCost(String cost) {
        this.earn = cost;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title){
        this.title=title;
    }

    //getters
    public String getCategory() {
        return category;
    }
    public int getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }
    public String getEarn() {
        return earn;
    }
}
