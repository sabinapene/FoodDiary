package com.github.sabinapene.fooddiary.Models;

/*import com.github.sabinapene.fooddiary.ActivityChangeGameName;
import com.github.sabinapene.fooddiary.ActivityNewPlayerPage;
import com.github.sabinapene.fooddiary.ActivityScorePage;*/

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DailyEntry {
    private String entryID = "";
    private String userID = "";
    private String date = "";
    private HashMap<Integer, Food> foods = new HashMap<Integer, Food>();

    public DailyEntry(){

    }

    public DailyEntry(String ID, String userID, String date){
        entryID = ID;
        this.userID = userID;
        this.date = date;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public HashMap getFoods() {
        return foods;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
