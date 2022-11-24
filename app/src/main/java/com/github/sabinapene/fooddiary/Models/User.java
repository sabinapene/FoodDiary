package com.github.sabinapene.fooddiary.Models;

import com.github.sabinapene.fooddiary.DailyEntryAdapter;

import java.util.ArrayList;

public class User {


    private String ID="";

    private String name ="";

    public User(){

    }
    public User(String ID, String name, int score){

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
}
