package com.github.sabinapene.fooddiary;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sabinapene.fooddiary.Models.BMI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityCalories extends AppCompatActivity {

    private static int calories=0;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        tv= (TextView) findViewById(R.id.textViewTotalCalories);
        tv.setText("Your total caloric consumption is "+calories);

    }

    public static void setCalories(int calories1){
       calories = calories1;
    }

}