package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside View Holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    //responsible for binding data to a particular view holder
    //responsible for taking data from particular position and putting it into specific view holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);
        //Bing the item to the specific view holder
        holder.bind(item);
    }

    @Override
    //number of items available in the data
    public int getItemCount() {
        return 0;
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        //get reference to the view that we can actually access in bind method
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //simple_list_item_1 contains id text1
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
        }
    }
}
