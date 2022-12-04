package com.github.sabinapene.fooddiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.github.sabinapene.fooddiary.Models.EntryFood;
import com.github.sabinapene.fooddiary.Models.Food;

import org.junit.Test;

import java.lang.reflect.Field;

public class FoodTest {


    //setters testing
    @Test
    public void testSetName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Food food = new Food();

        //when
        food.setName("apple");
        //then
        final Field field = food.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(food), "apple");
    }


    @Test
    public void testSetCalories_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        Food food = new Food();

        //when
        food.setCalories(123);
        //then
        final Field field = food.getClass().getDeclaredField("calories");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(food), 123);
    }

    //getters testing
    @Test
    public void testGetEntryName() throws NoSuchFieldException, IllegalAccessException {
        //given
        Food food = new Food();
        final Field field = food.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(food, "magic_values");

        //when
        final String result = food.getName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetCalories() {
        //given
        Food food = new Food();
        final int result = food.getCalories();

        //then
        assertEquals(-1, result);
    }


}



