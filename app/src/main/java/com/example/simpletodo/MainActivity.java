package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //implement model as List of String called items
    List<String> items;

    //Add a member variable from each view
    //get reference of each view in mainActivity
    //get handle of the btnAdd, etItem, rvItems in mainActivity
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define each member variable
        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);
        //each view has diff method
        //e.g. etItem.setText("I'm doing this from java!");

        //create mock data
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Do laundry");
        items.add("Do homework");

        //construct ItemsAdapter
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        //set the adapter on the rv
        rvItems.setAdapter(itemsAdapter);
        //default layoutmanager that puts list in vertical manner
        rvItems.setLayoutManager(new LinearLayoutManager(this));


        //get notified every time user taps on the button
        btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //get text from etItem --> todoItem
                 String todoItem = etItem.getText().toString();
                 //Add item to the model
                 items.add(todoItem);
                 //Notify adapter that an item has been added
                 itemsAdapter.notifyItemInserted(items.size()-1);
                 //clear the edit text after insertion
                 etItem.setText("");

                 //Add Toast message to notify user that item has been added successfully
                 Toast.makeText(getApplicationContext(), "Item was added successfully", Toast.LENGTH_SHORT).show();
             }
        });
    }
}