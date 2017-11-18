package com.example.hadideknache.financeapp;

/**
 * Created by hadideknache on 2017-09-21.
 */

public class BarCode {
    private String category;
    private String itemId;
    private String cost;
    private String itemName;
    private int id;

    public BarCode(String category, String itemId, String cost, String itemName, int id){
        this.category = category;
        this.itemId=itemId;
        this.cost=cost;
        this.itemName=itemName;
        this.id=id;
    }
    public BarCode(String category, String itemId, String cost, String itemName){
        this.category = category;
        this.itemId=itemId;
        this.cost=cost;
        this.itemName=itemName;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
