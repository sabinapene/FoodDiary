package com.github.sabinapene.fooddiary.Models;

public class Food {

    private String name ="";
    //calories per 100 grams
    private int calories = -1;

    public Food(){

    }
    public Food(  String name, int calories){

        this.name = name;
        this.calories = calories;
    }

    public int calculateCalories(int addCalories){
        return calories =+addCalories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
