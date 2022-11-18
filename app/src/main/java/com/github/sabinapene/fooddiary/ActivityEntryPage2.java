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
import com.github.sabinapene.fooddiary.Models.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityEntryPage2 extends AppCompatActivity {

    static String staticEntryId = "-1";
    static String entryDate = "";
    static String entryID="";

    private DailyEntry currentEntry = new DailyEntry();
    RecyclerView recyclerView;

    //firebase authentification
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db;

    private HashMap<String, Integer> entryFoodsMap = new HashMap<String, Integer>();
    private ArrayList<Food> foods = new ArrayList<Food>();


    DatabaseReference reference;
    DatabaseReference foodsReference;
    EntryFoodsAdapter2 adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        recyclerView = findViewById(R.id.rv);

        //initialising firebase authentification
        db = FirebaseDatabase.getInstance();
        reference = db.getReference().child("EntryFoods").child("-NH3pAvqLCsTaQ2TZbtc");

        //foodsReference = db.getReference();
        entryFoodsMap.clear();

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


                        //entryFoodsMap = (HashMap<String, Integer>) ds.getValue();
                        entryFoodsMap.put("oranges", 12);

                    }

                  validate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEntryPage2.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        };

        reference.addValueEventListener(valueEventListener);


    }



    public void validate(){
        /*if(entryFoodsMap.size()!=0 && entryFoodsMap.get(0)!=null) {
            for (String i : entryFoodsMap.keySet()) {
                System.out.println("key: " + i + " value: " + entryFoodsMap.get(i));
            }
        }
        else{
            Toast.makeText(ActivityEntryPage.this, "No food", Toast.LENGTH_SHORT).show();

        }*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        adapter = new EntryFoodsAdapter2(entryFoodsMap);
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