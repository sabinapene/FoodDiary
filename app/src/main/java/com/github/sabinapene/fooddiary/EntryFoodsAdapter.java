package com.github.sabinapene.fooddiary;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.DailyEntry;
import com.github.sabinapene.fooddiary.Models.EntryFood;
import com.github.sabinapene.fooddiary.Models.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryFoodsAdapter extends RecyclerView.Adapter<EntryFoodsAdapter.ViewHolder>{

    ArrayList<EntryFood> entryFoods = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public EntryFoodsAdapter(ArrayList<EntryFood> entryFoods, ArrayList<Food> foods){
        this.entryFoods = entryFoods;
        this.foods=foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int position1 = position;
        EntryFood tempFood = entryFoods.get(position1);
        holder.foodTextView.setText(tempFood.getFoodName()+" "+tempFood.getGrams()+" gr → "+searchCurrentFoodCalories(tempFood.getFoodName())*tempFood.getGrams()/100+" calories");

        ActivityEntryPage.setCalories(searchTotalFoodCalories());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    EntryFood currentEntryFood = entryFoods.get(position1);

                    if(ActivityEntryPage.getFoodDelete().equals(currentEntryFood.getId())){
                        ActivityEntryPage.setFoodDelete("");
                    } else {
                    ActivityEntryPage.setFoodDelete(currentEntryFood.getId());
                    }
                Log.i("deleteTag", "adapter tag "+currentEntryFood.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return entryFoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodTextView;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTextView = itemView.findViewById(R.id.foodtextview);
            checkBox = itemView.findViewById(R.id.foodcheckbox);

        }
    }

    private int searchCurrentFoodCalories(String name){

        int calories = 0;

        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getName().equals(name)) {
                calories= foods.get(i).getCalories();
            }
        }
        Log.i("Tag434", String.valueOf(foods.get(0).getName()));
        return calories;
    }

    public int searchTotalFoodCalories(){

         int calories = 0;

        for (int i = 0; i < foods.size(); i++) {
            calories+=foods.get(i).getCalories();
        }
        Log.i("totalcaltag", ""+calories);
        return calories;
    }
}
