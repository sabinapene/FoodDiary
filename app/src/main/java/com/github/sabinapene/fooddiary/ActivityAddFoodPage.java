package com.github.sabinapene.fooddiary;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.github.sabinapene.fooddiary.Models.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityAddFoodPage extends AppCompatActivity {

    RecyclerView recyclerView;

    //firebase authentification
    private FirebaseDatabase db;

    private ArrayList<Food> foods = new ArrayList<Food>();

    DatabaseReference reference;
    AddFoodAdapter adapter;
    Food currentFood=null;
    int grams=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_page);
        recyclerView = findViewById(R.id.rv);

        //initialising firebase
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Foods");

        retrieveData();


        findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentFood!=null && grams!=0){

                    }
                }});
    }


    private void retrieveData(){
        ValueEventListener valueEventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
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

    public void setCurrentFood(Food currentFood){
        this.currentFood = currentFood;
    }

    public void setGrams(int grams){
        this.grams = grams;
    }

}