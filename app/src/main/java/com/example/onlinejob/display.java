package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

public class display extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        listView = findViewById(R.id.listview);

        // Retrieve response data passed from user_login activity
        String response = getIntent().getStringExtra("response");

        // Parse response data using Gson
        Gson gson = new Gson();
        user data = gson.fromJson(response, user.class);
        String employeeList = data.toString();
 
        // Create an array adapter to display the list
//        ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Integer.parseInt(employeeList));
//        listView.setAdapter(adapter);
    }
}