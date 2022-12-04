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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int calculateSumOfCalories(ArrayList<EntryFood> list, ArrayList<Food> foodList){

        int caloriesSum=0;

        for (int i=0; i<list.size(); i++){
            int calories=0;
            EntryFood tempEntryFood = list.get(i);
            for(int j=0; j<foodList.size(); j++){
                Food tempFood = foodList.get(j);
                if(tempFood.getName().equals(tempEntryFood.getFoodName())){
                    calories=tempFood.getCalories();
                }
            }
            caloriesSum += tempEntryFood.calculateCalories(calories);
        }
        return caloriesSum;

    }

}
