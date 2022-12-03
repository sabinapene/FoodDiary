package com.github.sabinapene.fooddiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //firebase authentication
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db;
    private ArrayList<DailyEntry> entries = new ArrayList<DailyEntry>();
    private ArrayList<DailyEntry> currentEntries = new ArrayList<DailyEntry>();
    private String  userID;
    private DatabaseReference reference;
    private DailyEntryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);


        //initialising firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("DailyEntries");
        userID = firebaseAuth.getUid();
        entries.clear();

        retrieveData();


    }

    private void retrieveData(){
        ValueEventListener valueEventListener = new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    entries.clear();
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        DailyEntry entry = ds.getValue(DailyEntry.class);
                        entry.setEntryID(ds.getKey());
                        entries.add(entry);

                    }
                    search();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }

        };

        findViewById(R.id.entryfloatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Floating Act Btn Action goes here
                Date date2 = new Date(); // This object contains the current date value
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String dateString = formatter.format(date2);
                String date = "";
                for (int i = 0; i < 10; i++) {
                    date += dateString.toString().charAt(i);
                }

                boolean bool = true;
                for (int j = 0; j < entries.size(); j++) {
                    DailyEntry item = entries.get(j);
                    Log.i("entryTag", ""+item.getDate()+" "+date);

                    if (item.getDate().equals(date)) {
                        bool = false;
                    }}

                if (bool){
                    bool=true;
                addEntryFirebase();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                v.getContext().startActivity(intent);}
                else{
                    Toast.makeText(MainActivity.this, "Entry already exists ", Toast.LENGTH_SHORT).show();
                }
            }});

        reference.addValueEventListener(valueEventListener);
    }

    private void search(){

        currentEntries.clear();
        if(entries.size()!=0 && entries.get(0)!=null) {
            for (int i = 0; i < entries.size(); i++) {
                DailyEntry item = entries.get(i);
                if (item.getUserID().equals(userID)) {
                    currentEntries.add(item);
                }

            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.hasFixedSize();
            adapter = new DailyEntryAdapter(currentEntries);
            recyclerView.setAdapter(adapter);
        }
        else{
            Toast.makeText(MainActivity.this, "No entries", Toast.LENGTH_SHORT).show();

        }
    }


    private void addEntryFirebase() {


        //set up info to add to firebase
        Date date2 = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = formatter.format(date2);
        String date = "";
        for (int i = 0; i < 10; i++) {
            date += dateString.toString().charAt(i);
        }

        DailyEntry newDailyEntry = new DailyEntry("",userID, date);

        //initialising firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        //add to firebase
        DatabaseReference reference = db.getReference("DailyEntries");
        reference.push().setValue(newDailyEntry)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //entry added successfully
                        Toast.makeText(MainActivity.this, "Entry created ", Toast.LENGTH_SHORT).show();

                        //open main activity
                        //startActivity(new Intent(MainActivity.this, ActivityEntryPage.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //entry added failure
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)   {

        switch (item.getItemId())
        {
            case R.id.profileItem:
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ActivityProfile.class));
                break;

            case R.id.bmiItem:
                Toast.makeText(MainActivity.this, "BMI", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ActivityBMI.class));
                break;
        }

        return super.onOptionsItemSelected(item);

    }

}