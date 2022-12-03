package com.github.sabinapene.fooddiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sabinapene.fooddiary.Models.BMI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityBMI extends AppCompatActivity {


    private String  userID;
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("BMI");
        userID = firebaseAuth.getUid();
        tv = (TextView) findViewById(R.id.textViewBMIResult);

        ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        BMI bmi = ds.getValue(BMI.class);
                        if(bmi.getUserID().equals(userID)){
                            String strheight =""+ (bmi.getHeight()/100)+"."+(bmi.getHeight()%100);
                            float height = Float.parseFloat(strheight);

                            tv.setText("Your current BMI is "+bmi.getWeight()/(height*height));
                            Log.i("bmiTag", height+" "+strheight);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityBMI.this, "Error", Toast.LENGTH_SHORT).show();
            }
        };
        reference.addValueEventListener(valueEventListener);
    }
}