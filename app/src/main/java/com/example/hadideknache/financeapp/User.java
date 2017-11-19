package com.example.hadideknache.financeapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is a pojo class which each user have a userobject with its information
 * The user is implementing parcelable which can be saved and sent to other activities
 * Created by Hadi Deknache on 2017-09-13.
 */

public class User implements Parcelable{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String pass;

    /**
     * User constructor
     * @param id id set by the database
     * @param name the name of the user
     * @param surname the surname of the user
     * @param email the email of the user
     * @param pass the password of the user
     */
    public User(int id,String name, String surname, String email, String pass){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;

    }

    /**
     * User constructor
     * @param name the name of the user
     * @param surname the surname of the user
     * @param email the email of the user
     * @param pass the password of the user
     */
    public User(String name, String surname, String email, String pass) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;

    }

    //Setters!
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    //Getters!
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getPass() {
        return pass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method writes data to parcel for saving it
     * @param parcel the parcel to pack the information to
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(email);
        parcel.writeString(pass);
    }

    /**
     * Method creating the parcel of userobjects
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source.readInt(),source.readString(),source.readString(),source.readString(),source.readString());
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
