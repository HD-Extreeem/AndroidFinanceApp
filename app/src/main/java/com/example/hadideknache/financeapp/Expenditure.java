package com.example.hadideknache.financeapp;

/**
 * This class is a pojo class handling the expenditures in a object
 * Created by hadideknache on 2017-09-17.
 */

public class Expenditure {
    private int id;
    private String category;
    private String time;
    private String date;
    private String cost;
    private String title;

    /**
     * Constructor of expenditure
     * @param id the id given for the expenditure
     * @param category the category of the item
     * @param time the time when bought
     * @param date the date when bought
     * @param cost the price of the object
     * @param title the name of the object
     */
    public Expenditure(int  id, String category,String time,String date,String cost,String title){
        this.id=id;
        this.category = category;
        this.time=time;
        this.date=date;
        this.cost=cost;
        this.title=title;
    }

    /**
     * Constructor of expenditure
     * @param category the category of the item
     * @param time the time when bought
     * @param date the date when bought
     * @param cost the price of the object
     * @param title the name of the object
     */
    public Expenditure(String category, String time, String date, String cost,String title) {
        this.category = category;
        this.time=time;
        this.date=date;
        this.cost=cost;
        this.title=title;
    }

    /**
     * Method for getting the price of object
     * @return the price
     */
    public String getCost() {
        return cost;
    }

    /**
     * Method for setting the price of item
     * @param cost the price of the item
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * Method for getting the date of the item
     * @return the date of the item
     */
    public String getDate() {
        return date;
    }

    /**
     * Method for setting the date when bought
     * @param date the date when bought
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method for getting the time
     * @return the time when bought
     */
    public String getTime() {
        return time;
    }

    /**
     * Method for setting the time
     * @param time the time when bought
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Method for getting the category of item
     * @return the category of the item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Method for setting the category
     * @param category the category of the item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method for getting the id of item
     * @return the item id
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting the id of item
     * @param id item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for getting the title
     * @return the name of the item
     */
    public String getTitle(){
        return title;
    }

    /**
     * Method for setting the title/name of the item
     * @param title the name of the item
     */
    public void setTitle(String title){
        this.title=title;
    }
}

