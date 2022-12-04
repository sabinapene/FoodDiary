package com.github.sabinapene.fooddiary.Models;

public class BMI {


    private String userID="";

    private int height=0;

    private int weight=0;

    public BMI(){

    }
    public BMI(String userID, int height, int weight){
        this.userID = userID;
        this.height = height;
        this.weight = weight;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public float calculateBMI(){
        int heightMeters=height/100;
        int heightCm = height%100;
        String heightStr = ""+ heightMeters +"."+heightCm;

        return weight/(Float.parseFloat(heightStr)*Float.parseFloat(heightStr));
    }
}
