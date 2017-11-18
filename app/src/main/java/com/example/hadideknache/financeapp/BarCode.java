package com.example.hadideknache.financeapp;

/**
 * This is pojo for barcode
 * Used for barcodes that is being scanned for keeping them in database
 * Created by Hadi Deknache on 2017-09-21.
 */

public class BarCode {
    private String category;
    private String itemId;
    private String cost;
    private String itemName;
    private int id;

    /**
     * Constructor for barcode class
     * @param category category of the item that is scanned
     * @param itemId id of the scanned item
     * @param cost the price of the item
     * @param itemName the name of the item
     */
    public BarCode(String category, String itemId, String cost, String itemName){
        this.category = category;
        this.itemId=itemId;
        this.cost=cost;
        this.itemName=itemName;
    }


    /**
     * method for getting the itemname
     * @return the name of the item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Method for getting the category of the object
     * @return the category of the object
     */
    public String getCategory() {
        return category;
    }

    /**
     * Method for setting the category of the item
     * @param category the category of item
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Method for getting the cost of the item
     * @return the price of the item
     */
    public String getCost() {
        return cost;
    }

    /**
     * Method for setting the price of the item
     * @param cost the price of the item
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * Method for getting the id of item
     * @return the id of item that was scanned
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting the id of item
     * @param id the id of the itm
     */
    public void setId(int id) {
        this.id = id;
    }
}
