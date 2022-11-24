package com.github.sabinapene.fooddiary.Models;

public class EntryFood {

    private String id="";
    private String foodName ="";
    private String entryDate ="";
    private int grams = -1;

    public EntryFood(){

    }
    public EntryFood(String entryDate, String name, int grams){
        this.entryDate = entryDate;
        this.foodName = name;
        this.grams = grams;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String name) {
        this.foodName = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public String getEntryDate(){return entryDate;}

    public void setEntryDate(String entryDate){ this.entryDate = entryDate; }

}
