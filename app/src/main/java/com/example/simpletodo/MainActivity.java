package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
        ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        //set the adapter on the rv
        rvItems.setAdapter(itemsAdapter);
        //default layoutmanager that puts list in vertical manner
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }
}