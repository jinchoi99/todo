package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    //ref views
    EditText etItem;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById(R.id.etItem);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar().setTitle("Edit item");

        //prepopulate clicked item
        etItem.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        //implement save button
        //click button when user done with editing
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent which will contain the results

                //pass the data (results of editing)

                //set the result of the intent
                //finish activity, close the screen and go back to mainactivity screen



            }
        });

    }
}