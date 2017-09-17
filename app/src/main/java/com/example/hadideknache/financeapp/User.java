package com.example.hadideknache.financeapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hadideknache on 2017-09-13.
 */

public class User implements Parcelable{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String pass;

    public User(int id,String name, String surname, String email, String pass){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;

    }

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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(email);
        parcel.writeString(pass);
    }
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source.readInt(),source.readString(),source.readString(),source.readString(),source.readString());
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
