package com.github.sabinapene.fooddiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.Food;

import java.util.ArrayList;

public class AddFoodAdapter extends RecyclerView.Adapter<AddFoodAdapter.ViewHolder>{

    ArrayList<Food> foods = new ArrayList<Food>();

    public AddFoodAdapter(ArrayList<Food> foods){
        this.foods = foods;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.add_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.addfoodtextview.setText(foods.get(position).getName()+" : "+foods.get(position).getCalories()+" calories ");


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView addfoodtextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addfoodtextview = itemView.findViewById(R.id.addfoodtextview);

        }

    }

}
