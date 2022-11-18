package com.github.sabinapene.fooddiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntryFoodsAdapter2 extends RecyclerView.Adapter<EntryFoodsAdapter2.ViewHolder>{

    HashMap<String, Integer> entryFoodsMap = new HashMap<String, Integer>();
    ArrayList<Food> foods = new ArrayList<Food>();

    public EntryFoodsAdapter2(HashMap<String, Integer> entryFoodsMap){
        this.entryFoodsMap = entryFoodsMap;
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

        int i = 0;
        for (Map.Entry<String, Integer> entry : entryFoodsMap.entrySet()) {
            if(position == i){
                String food = entry.getKey();
                int quantity = entry.getValue();

                holder.foodTextView.setText(food+" "+quantity+" gr → "+quantity*47/100+" calories");


                break;
            }
            i++;
        }

    }

    @Override
    public int getItemCount() {
        return entryFoodsMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTextView = itemView.findViewById(R.id.foodtextview);

        }

    }

    public void setEntryFoodsMap(HashMap<String, Integer> entryFoodsMap){
        this.entryFoodsMap = entryFoodsMap;
    }

    private void searchCurrentFoodCalories(){


    }
}
