package com.github.sabinapene.fooddiary;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.github.sabinapene.fooddiary.Models.EntryFood;
import com.github.sabinapene.fooddiary.Models.Food;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityAddFoodPage extends AppCompatActivity {

    RecyclerView recyclerView;

    //firebase
    private FirebaseDatabase db;

    private static ArrayList<Food> foods = new ArrayList<Food>();

    DatabaseReference reference;
    AddFoodAdapter adapter;
    Food currentFood=null;
    int grams=0;
    static String entryDate="";
    private static ArrayList<EntryFood> entryFoods = new ArrayList<>();

        public static void setEntryDate(String date){
            entryDate = date;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_page);
        recyclerView = findViewById(R.id.rv);

        //initialising firebase
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Foods");

        retrieveData();

        Log.i("entryfoodsTag", ""+entryFoods.get(0).getFoodName()+entryFoods.get(1).getFoodName());

        findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//iterate through views and foods, they have the same count and order
                    EntryFood entryFood = null;
                    for (int i = 0; i < recyclerView.getChildCount(); i++)
                    {
                        //recyclerView.getChildAt(i);

                        Food food = foods.get(i);
                        String stringGrams = ((TextView) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.addfoodedittext)).getText().toString();

                        if(!stringGrams.equals("")){

                            entryFood = new EntryFood(entryDate, food.getName(), Integer.parseInt( stringGrams ));
                            }
                    }
                    if(entryFood!=null){
                        addEntryFoodFirebase(entryFood);
                        }
            }

        });




    }


    private void retrieveData(){
        ValueEventListener valueEventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    foods.clear();
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        Food food = ds.getValue(Food.class);
                        //Food food = new Food("berries", 100);
                        foods.add(food);

                    }

                  validate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityAddFoodPage.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        };

        reference.addValueEventListener(valueEventListener);
    }

    public void validate(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        adapter = new AddFoodAdapter(foods);
        recyclerView.setAdapter(adapter);
    }

    private void addEntryFoodFirebase(EntryFood foo) {

        //initialising firebase
        db = FirebaseDatabase.getInstance();


        //add to firebase
        DatabaseReference reference1 = db.getReference("EntryFoodsList");
        reference1.push().setValue(foo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //entryFood added successfully
                        Toast.makeText(ActivityAddFoodPage.this, "Food added ", Toast.LENGTH_SHORT).show();

                        //open ActivityEntryPage
                        //startActivity(new Intent(ActivityAddFoodPage.this, ActivityEntryPage.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //entry added failure
                        Toast.makeText(ActivityAddFoodPage.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public static void setEntryFoods(ArrayList<EntryFood> entryFoods2){entryFoods=entryFoods2;}
}