package com.github.sabinapene.fooddiary;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sabinapene.fooddiary.Models.BMI;
import com.github.sabinapene.fooddiary.Models.Food;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityChangeBMI extends AppCompatActivity {


    private String  userID;
    private SeekBar weightSeekBar;
    private SeekBar heightSeekBar;
    private TextView textviewWeight;
    private TextView textviewHeight;
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    int weight=0;
    int height=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bmi);

        weightSeekBar = (SeekBar) findViewById(R.id.seekBarWeight);
        heightSeekBar = (SeekBar) findViewById(R.id.seekBarHeight);
        textviewWeight = (TextView) findViewById(R.id.textViewWeight);
        textviewHeight = (TextView) findViewById(R.id.textViewHeight);

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textviewWeight.setText("Your weight is "+i+" kg");
                weight=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textviewHeight.setText("Your height is "+i+" cm");
                height=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        findViewById(R.id.saveBMIBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //initialising firebase authentication
                firebaseAuth = FirebaseAuth.getInstance();
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("BMI");
                userID = firebaseAuth.getUid();


                   //delete in firebase
                   db.getReference().child(userID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                pushTodB();
                               }
                           })
                           .addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   //failure
                                   pushTodB();
                               }
                           });





    }

    private void pushTodB() {
            //add to firebase
            BMI bmi = new BMI(userID, height, weight);
            reference.child(bmi.getUserID()).setValue(bmi)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // added successfully
                            Toast.makeText(ActivityChangeBMI.this, "BMI added ", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(ActivityChangeBMI.this, ActivityProfile.class));
                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //failure
                            Toast.makeText(ActivityChangeBMI.this, "Error", Toast.LENGTH_SHORT).show();

                        }
                    });}  });
    }

}