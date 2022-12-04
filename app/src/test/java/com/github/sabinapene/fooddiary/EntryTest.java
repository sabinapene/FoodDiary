package com.github.sabinapene.fooddiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.github.sabinapene.fooddiary.Models.EntryFood;
import com.github.sabinapene.fooddiary.Models.Food;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class EntryTest {

    DailyEntry entry = new DailyEntry("1234","userID","04-12-2022");

    Food food1 = new Food("blueberries", 57);
    Food food2 = new Food("tuna", 132);
    Food food3 = new Food("salmon", 208);
    Food food4 = new Food("shrimps", 99);
    Food food5 = new Food("orange", 47);
    Food food6 = new Food("apple", 52);
    Food food7 = new Food("salami", 336);
    Food food8 = new Food("spinach", 23);
    Food food9 = new Food("chocolate", 546);
    Food food10 = new Food("tomato", 18);
    Food food11 = new Food("cookies", 502);

    ArrayList<Food> foodList = new ArrayList<>();

    //Calculate Sum of Calories Testing
    //int result
    @Test
    public void calculateTotalCalories_assertEquals1() {
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);


        EntryFood e1 = new EntryFood("04-12-2022", "apple", 230);
        EntryFood e2 = new EntryFood("04-12-2022", "orange", 30);
        EntryFood e3 = new EntryFood("04-12-2022", "tuna", 130);
        EntryFood e4 = new EntryFood("04-12-2022", "shrimps", 155);

        ArrayList<EntryFood> list = new ArrayList<>();

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        int sumOfCalories = entry.calculateSumOfCalories(list, foodList);

        assertEquals(119+14+171+153, sumOfCalories);
    }

    //float result
    @Test
    public void calculateTotalCalories_assertNotEquals1() {
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);


        EntryFood e1 = new EntryFood("04-12-2022", "apple", 230);
        EntryFood e2 = new EntryFood("04-12-2022", "orange", 30);
        EntryFood e3 = new EntryFood("04-12-2022", "tuna", 130);
        EntryFood e4 = new EntryFood("04-12-2022", "shrimps", 155);

        ArrayList<EntryFood> list = new ArrayList<>();

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        int sumOfCalories = entry.calculateSumOfCalories(list, foodList);

        assertNotEquals(119.22+14.1+171.15+153.3, sumOfCalories);
    }

    //int result
    @Test
    public void calculateTotalCalories_assertEquals2() {
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);


        EntryFood e1 = new EntryFood("03-12-2022", "spinach", 30);
        EntryFood e2 = new EntryFood("03-12-2022", "chocolate", 133);
        EntryFood e3 = new EntryFood("03-12-2022", "tomato", 25);
        EntryFood e4 = new EntryFood("03-12-2022", "cookies", 55);
        EntryFood e5 = new EntryFood("03-12-2022", "salami", 254);
        EntryFood e6 = new EntryFood("03-12-2022", "blueberries", 125);
        EntryFood e7 = new EntryFood("03-12-2022", "salmon", 300);

        ArrayList<EntryFood> list = new ArrayList<>();

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);

        int sumOfCalories = entry.calculateSumOfCalories(list, foodList);

        assertEquals(6+726+4+276+853+71+624, sumOfCalories);
    }

    //float result
    @Test
    public void calculateTotalCalories_assertNotEquals2() {
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);


        EntryFood e1 = new EntryFood("03-12-2022", "spinach", 30);
        EntryFood e2 = new EntryFood("03-12-2022", "chocolate", 133);
        EntryFood e3 = new EntryFood("03-12-2022", "tomato", 25);
        EntryFood e4 = new EntryFood("03-12-2022", "cookies", 55);
        EntryFood e5 = new EntryFood("03-12-2022", "salami", 254);
        EntryFood e6 = new EntryFood("03-12-2022", "blueberries", 125);
        EntryFood e7 = new EntryFood("03-12-2022", "salmon", 300);

        ArrayList<EntryFood> list = new ArrayList<>();

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);

        int sumOfCalories = entry.calculateSumOfCalories(list, foodList);

        assertNotEquals(6.4+726.7+4+276.3+853+71.1+624, sumOfCalories);
    }

    //setters testing
    @Test
    public void testSetEntryID_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();

        //when
        de.setEntryID("123");
        //then
        final Field field = de.getClass().getDeclaredField("entryID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(de), "123");
    }

    @Test
    public void testSetUserID_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();

        //when
        de.setUserID("123");
        //then
        final Field field = de.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(de), "123");
    }

    @Test
    public void testSetDate_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();

        //when
        de.setDate("02-12-2022");
        //then
        final Field field = de.getClass().getDeclaredField("date");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(de), "02-12-2022");
    }

    //getters testing

    @Test
    public void testGetUserID() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();
        final Field field = de.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(de, "magic_values");

        //when
        final String result = de.getUserID();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetEntryID() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();
        final Field field = de.getClass().getDeclaredField("entryID");
        field.setAccessible(true);
        field.set(de, "magic_values");

        //when
        final String result = de.getEntryID();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetDate() throws NoSuchFieldException, IllegalAccessException {
        //given
        final DailyEntry de = new DailyEntry();
        final Field field = de.getClass().getDeclaredField("date");
        field.setAccessible(true);
        field.set(de, "magic_values");

        //when
        final String result = de.getDate();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

}



