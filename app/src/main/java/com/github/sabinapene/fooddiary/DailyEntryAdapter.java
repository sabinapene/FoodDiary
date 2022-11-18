package com.github.sabinapene.fooddiary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sabinapene.fooddiary.Models.DailyEntry;

import java.util.List;

public class DailyEntryAdapter extends RecyclerView.Adapter<DailyEntryAdapter.ViewHolder>{
    List<DailyEntry> entries;
    DailyEntry currentDailyEntry = new DailyEntry();

    public DailyEntry getCurrentDailyEntry()
    {
        return currentDailyEntry;
    }
    public DailyEntryAdapter(List<DailyEntry> entries){
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.entry_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.buttonentrydate.setText(entries.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button buttonentrydate;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonentrydate = itemView.findViewById(R.id.entrydatebutton);

            buttonentrydate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    currentDailyEntry = entries.get(getAdapterPosition());

                    ActivityEntryPage.setEntryId(currentDailyEntry.getEntryID());
                    ActivityEntryPage.setEntryDate(currentDailyEntry.getDate());
                    ActivityEntryPage.setEntryID(currentDailyEntry.getEntryID());

                    Intent intent = new Intent(itemView.getContext(), ActivityEntryPage.class);
                    v.getContext().startActivity(intent);
                }
            });



        }

    }
}
