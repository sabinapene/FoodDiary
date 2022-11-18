package com.github.sabinapene.fooddiary;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityEntryPage extends AppCompatActivity {

    static String staticEntryId = "-1";
    static String entryDate = "";
    static String entryID="";

    private DailyEntry currentEntry;
    RecyclerView recyclerView;

    //firebase authentification
    private FirebaseDatabase db;

    private ArrayList<Food> foods= new ArrayList<>();
    private ArrayList<EntryFood> entryFoods= new ArrayList<>();
    private ArrayList<EntryFood> currentEntryFoods = new ArrayList<>();


    DatabaseReference reference;
    DatabaseReference foodReference;
    EntryFoodsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        recyclerView = findViewById(R.id.rv);

        //initialising firebase authentification
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("EntryFoodsList");
        foodReference = db.getReference("Foods");

        //foodsReference = db.getReference();
        entryFoods.clear();

        EntryFood entryFood1 = new EntryFood("17-11-2022 10:04:07","berry", 100);
        entryFoods.add(entryFood1);

        Food food1 = new Food("berry", 57);
        foods.add(food1);

        retrieveData();

        TextView textView = findViewById(R.id.entrytextView);
        textView.setText(entryDate);

        findViewById(R.id.addfoodfloatingActionButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Floating Act Btn Action goes here
                    Intent intent = new Intent(getApplicationContext(), ActivityAddFoodPage.class);
                    v.getContext().startActivity(intent);
                }});
    }


    private void retrieveData(){
        ValueEventListener valueEventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        EntryFood foo = ds.getValue(EntryFood.class);
                        entryFoods.add(foo);

                    }
                }
                validate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEntryPage.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        };

        reference.addValueEventListener(valueEventListener);


    }

    private void retrieveFoodListData(){
        ValueEventListener valueEventListener2 = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    foods.clear();
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        Food food = ds.getValue(Food.class);
                        foods.add(food);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEntryPage.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        };

        foodReference.addValueEventListener(valueEventListener2);


    }



    public void validate(){

        currentEntryFoods.clear();
        if(entryFoods.size()!=0 && entryFoods.get(0)!=null) {
            for (int i = 0; i < entryFoods.size(); i++) {
                EntryFood foo = entryFoods.get(i);
                if (foo.getEntryDate().equals(entryDate)) {
                    currentEntryFoods.add(foo);
                }
            }
            retrieveFoodListData();
        }
        else{
            Toast.makeText(ActivityEntryPage.this, "No food", Toast.LENGTH_SHORT).show();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        adapter = new EntryFoodsAdapter(currentEntryFoods, foods);
        recyclerView.setAdapter(adapter);
    }


    /*private void deletePlayers(){

        //iterate players
        for (int i = 0; i < currentPlayers.size(); i++)
        {
            Player player = currentPlayers.get(i);
            String playerID = player.getID();
            //delete from firebase
            reference.child(playerID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    //score cleared successfully
                    Toast.makeText(getApplicationContext(), "Table Cleared", Toast.LENGTH_SHORT).show();


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //failure
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                        }
                    });


        }
    }*/

    public static void setEntryId(String newEntryId){
        staticEntryId = newEntryId;
    }

    public static void setEntryDate(String newEntryDate){
        entryDate = newEntryDate;
    }

    public static void setEntryID(String newEntryID){
        entryID = newEntryID;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)   {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.scoremenu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)   {

        switch (item.getItemId())
        {
            case R.id.resetScoreItem:

                //iterate through views and players, they have the same count and order
                for (int i = 0; i < recyclerView.getChildCount(); i++)
                {
                    Player player = currentPlayers.get(i);

                    //update in firebase
                    reference.child(player.getID()).child("score").setValue(0).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            //score reseted successfully
                            Toast.makeText(getApplicationContext(), "Score Reseted", Toast.LENGTH_SHORT).show();

                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //failure
                                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                                }
                            });


                }
                break;

            case R.id.clearTableItem:
               deletePlayers();
                break;

            case R.id.deleteGameItem:
                deletePlayers();

                //delete from firebase
                db.getReference("games").child(gameID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //score cleared successfully
                        Toast.makeText(getApplicationContext(), "Game deleted", Toast.LENGTH_SHORT).show();

                        //open main activity
                        finish();

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //failure
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                            }
                        });
                break;

        }

        return super.onOptionsItemSelected(item);

    }*/





}