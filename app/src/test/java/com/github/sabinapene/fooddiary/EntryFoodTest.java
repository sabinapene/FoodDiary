package com.github.sabinapene.fooddiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.github.sabinapene.fooddiary.Models.BMI;
import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.github.sabinapene.fooddiary.Models.EntryFood;

import org.junit.Test;

import java.lang.reflect.Field;

public class EntryFoodTest {

    EntryFood entryFood = new EntryFood("04-12-2022","apple", 133);



    //Calculate Total Calories Testing
    //float apple
    @Test
    public void calculateTotalCalories_assertEquals1() {

        int calories = entryFood.calculateCalories(52);
        assertEquals(69, calories);
    }

    @Test
    public void calculateTotalCalories_assertNotEquals1() {

        int calories = entryFood.calculateCalories(52);
        assertNotEquals(69.16, calories);
    }

    //int apple
    @Test
    public void calculateTotalCalories_assertEquals2() {
        entryFood.setGrams(250);
        int calories = entryFood.calculateCalories(52);
        assertEquals(130, calories);
    }

    @Test
    public void calculateTotalCalories_assertNotEquals2() {
        entryFood.setGrams(250);
        int calories = entryFood.calculateCalories(52);
        assertNotEquals(69, calories);
    }

    //int tuna
    @Test
    public void calculateTotalCalories_assertEquals3() {
        entryFood.setGrams(175);
        entryFood.setFoodName("Tuna");
        int calories = entryFood.calculateCalories(132);
        assertEquals(231, calories);
    }

    @Test
    public void calculateTotalCalories_assertNotEquals3() {
        entryFood.setGrams(175);
        entryFood.setFoodName("Tuna");
        int calories = entryFood.calculateCalories(132);
        assertNotEquals(230, calories);
    }

    //float tuna
    @Test
    public void calculateTotalCalories_assertEquals4() {
        entryFood.setGrams(163);
        entryFood.setFoodName("Tuna");
        int calories = entryFood.calculateCalories(132);
        assertEquals(215, calories);
    }

    @Test
    public void calculateTotalCalories_assertNotEquals4() {
        entryFood.setGrams(163);
        entryFood.setFoodName("Tuna");
        int calories = entryFood.calculateCalories(132);
        assertNotEquals(215.16, calories);
    }





    //setters testing
    @Test
    public void testSetId_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();

        //when
        ef.setId("123");
        //then
        final Field field = ef.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ef), "123");
    }

    @Test
    public void testSetGrams_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();

        //when
        ef.setGrams(123);
        //then
        final Field field = ef.getClass().getDeclaredField("grams");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ef), 123);
    }

    @Test
    public void testSetFoodName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();

        //when
        ef.setFoodName("apple");
        //then
        final Field field = ef.getClass().getDeclaredField("foodName");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ef), "apple");
    }

    @Test
    public void testSetEntryDate_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();

        //when
        ef.setEntryDate("01-12-2022");
        //then
        final Field field = ef.getClass().getDeclaredField("entryDate");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ef), "01-12-2022");
    }

    //getters testing
    @Test
    public void testGetEntryDate() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();
        final Field field = ef.getClass().getDeclaredField("entryDate");
        field.setAccessible(true);
        field.set(ef, "magic_values");

        //when
        final String result = ef.getEntryDate();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetFoodName() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();
        final Field field = ef.getClass().getDeclaredField("foodName");
        field.setAccessible(true);
        field.set(ef, "magic_values");

        //when
        final String result = ef.getFoodName();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetID() throws NoSuchFieldException, IllegalAccessException {
        //given
        final EntryFood ef = new EntryFood();
        final Field field = ef.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(ef, "magic_values");

        //when
        final String result = ef.getId();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetGrams() {
        //given
        final EntryFood ef = new EntryFood();
        final int result = ef.getGrams();

        //then
        assertEquals(-1, result);
    }


}



