package com.github.sabinapene.fooddiary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.github.sabinapene.fooddiary.Models.BMI;
import com.github.sabinapene.fooddiary.Models.EntryFood;

import org.junit.Test;

import java.lang.reflect.Field;

public class BMITest {

    BMI bmi = new BMI("userID123",173,65);



    //Calculate BMI Testing
    //int result
    @Test
    public void calculateBMI_assertEquals1() {
        bmi.setHeight(200);
        bmi.setWeight(80);
        Float bmiIndex = bmi.calculateBMI();

        assertEquals(20, bmiIndex, 0.001);
    }

    @Test
    public void calculateBMI_assertNotEquals1() {
        bmi.setHeight(200);
        bmi.setWeight(80);
        Float bmiIndex = bmi.calculateBMI();

        assertNotEquals(Float.parseFloat("20.7180660897"), bmiIndex, 0.001);
    }

    //float result
    @Test
    public void calculateBMI_assertEquals2() {
        Float bmiIndex = bmi.calculateBMI();

        assertEquals(Float.parseFloat("21.7180660897"), bmiIndex, 0.001);
    }

    @Test
    public void calculateBMI_assertNotEquals2() {
        Float bmiIndex = bmi.calculateBMI();

        assertNotEquals(21, bmiIndex, 0.001);
    }


    //setters testing
    @Test
    public void testSetUserId_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();

        //when
        bmi1.setUserID("123");
        //then
        final Field field = bmi1.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(bmi1), "123");
    }

    @Test
    public void testSetWeight_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();

        //when
        bmi1.setWeight(56);
        //then
        final Field field = bmi1.getClass().getDeclaredField("weight");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(bmi1), 56);
    }

    @Test
    public void testSetHeight_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();

        //when
        bmi1.setHeight(156);
        //then
        final Field field = bmi1.getClass().getDeclaredField("height");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(bmi1), 156);
    }



    //getters testing
    @Test
    public void testGetUserID() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();
        final Field field = bmi1.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        field.set(bmi1, "magic_values");

        //when
        final String result = bmi1.getUserID();

        //then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }

    @Test
    public void testGetWeight() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();
        final int result = bmi1.getWeight();

        //then
        assertEquals(0, result);
    }

    @Test
    public void testGetHeight() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BMI bmi1 = new BMI();
        final int result = bmi1.getHeight();

        //then
        assertEquals(0, result);
    }

}



