package com.example.simpletodo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POSITION = "item_position";
    public static final int EDIT_TEXT_CODE = 20;

    //implement model as List of String called items
    List<String> items;

    //Add a member variable from each view
    //get reference of each view in mainActivity
    //get handle of the btnAdd, etItem, rvItems in mainActivity
    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    ItemsAdapter itemsAdapter;

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

        /*//create mock data
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Do laundry");
        items.add("Do homework");*/

        //Call loadItems at the start
        loadItems();

        ItemsAdapter.OnLongClickListener OnLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                //Delete the item (in position) from the model
                items.remove(position);
                //notify the adapter
                itemsAdapter.notifyItemRemoved(position);

                //Add Toast message to notify user that item has been removed successfully
                Toast.makeText(getApplicationContext(), "Item was removed successfully", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                //Check if  single click works
                Log.d("MainActivity", "single click at position "+position);

                //open up edit activity:
                //create the new activity (activity = screen)
                //intent = can think of it as request to android system
                //intent/request here is to open up another activity
                Intent i = new Intent(MainActivity.this, EditActivity.class); //MainActivity.this vs EditActivity.class => .this is an instance, .class is class

                //pass the data being edited
                i.putExtra(KEY_ITEM_TEXT, items.get(position));
                i.putExtra(KEY_ITEM_POSITION, position);

                //display the activity
                startActivityForResult(i, EDIT_TEXT_CODE);

            }
        };

        //construct ItemsAdapter
        itemsAdapter = new ItemsAdapter(items, OnLongClickListener, onClickListener);

        //set the adapter on the rv
        rvItems.setAdapter(itemsAdapter);

        //default layoutmanager that puts list in vertical manner
        //actually display the items in list form on screen
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

                 saveItems();
             }
        });
    }

    //handle the result of the edit activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE){
            //retrieve updated text value
            String itemText = data.getStringExtra(KEY_ITEM_TEXT);
            //extract the original position of the edited item from the position key
            int position = data.getExtras().getInt(KEY_ITEM_POSITION);

            //update the model with new item at the right position
            items.set(position, itemText);
            //notify the adapter
            itemsAdapter.notifyItemChanged(position);
            //persist the changes
            saveItems();

            //Add Toast message to notify user that item has been updated successfully
            Toast.makeText(getApplicationContext(), "Item was updated successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            //warning message
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
    }

    private File getDataFile(){
        //get this app's directory, name file data.txt, this file is where we will store data
        return new File(getFilesDir(),"data.txt");
    }

    //This function will load items by reading every line of the data file
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(),Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList<>();
        }
    }//call in the beginning of app start

    //This function saves items by writing them into the data file
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }//call whenever add or remove item
}