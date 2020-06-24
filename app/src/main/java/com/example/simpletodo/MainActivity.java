package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //implement model as List of String called items
    List<String> items;

    //Add member variable of each view
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create mock data
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Do laundry");
        items.add("Do homework");

        //get reference of each view in mainActivity
        //get handle of the btnAdd, etItem, rvItems in mainActivity
    }
}