package com.example.simpletodo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    //need info to be passed in from MainActivity.java to construct adapter
    List<String> items;
    public ItemsAdapter(List<String> items){
        this.items = items;
    }

    @NonNull
    @Override
    //responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflater to inflate view
        //wrap it inside View Holder and return it

        return null;
    }

    @Override
    //responsible for taking data from particular position and putting it into viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    //number of items available in the data
    public int getItemCount() {
        return 0;
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
