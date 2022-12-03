package com.github.sabinapene.fooddiary.Models;

import com.github.sabinapene.fooddiary.DailyEntryAdapter;

import java.util.ArrayList;

public class User {


    private String ID="";

    private String name ="";

    private int height=0;

    private int weight=0;

    public User(){

    }
    public User(String ID, String name){

        this.ID = ID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
