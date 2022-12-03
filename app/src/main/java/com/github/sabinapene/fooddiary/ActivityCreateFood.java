package com.github.sabinapene.fooddiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sabinapene.fooddiary.Models.Food;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCreateFood extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference reference;
    private String name;
    private String strCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        db = FirebaseDatabase.getInstance();
        reference = db.getReference().child("Foods");

        Button createButton = (Button) findViewById( R.id.createFoodBtn );

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateData();

            }

        });

    }

    private void validateData() {
        //get data
        name="";
        EditText editTextName = findViewById(R.id.nameEt);
        name = editTextName.getText().toString().trim();


        strCalories="";
        EditText editTextCalories = findViewById(R.id.caloriesEt);
        strCalories = editTextCalories.getText().toString().trim();


        if(name.equals("")){
            //empty string
            Toast.makeText(ActivityCreateFood.this, "Enter Food Name", Toast.LENGTH_SHORT).show();

        }
        else if(strCalories.equals("")){
            //no password entered

            Toast.makeText(ActivityCreateFood.this, "Enter Calories", Toast.LENGTH_SHORT).show();

        }
        else {
            //valid data, proceed with firebase sign up
            pushToDB();
        }
    }

    private void pushToDB() {
        Log.i("createFoodTag", ""+Integer.parseInt(strCalories));
        Food food = new Food(name, Integer.parseInt(strCalories));

        //add to firebase
        reference.push().setValue(food)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //entryFood added successfully
                        Toast.makeText(ActivityCreateFood.this, "Food created ", Toast.LENGTH_SHORT).show();

                        //open ActivityEntryPage
                        //startActivity(new Intent(ActivityCreateFood.this, ActivityEntryPage.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //entry added failure
                        Toast.makeText(ActivityCreateFood.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}