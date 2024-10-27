package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

public class user_dashboard extends AppCompatActivity {
Intent intent;
String username;
String userpassword;
ImageView jobsearch,profile,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=getIntent();
        username=intent.getStringExtra("username");
        userpassword=intent.getStringExtra("userpassword");
        setContentView(R.layout.activity_user_dashboard);
        jobsearch=findViewById(R.id.jobsearch);
        profile=findViewById(R.id.profileicon);
        logout=findViewById(R.id.logouticon);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(user_dashboard.this, user_login.class);
                startActivity(intent1);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(user_dashboard.this, user_profile.class);
                intent2.putExtra("username",username);
               intent2.putExtra("userpassword",userpassword);
                startActivity(intent2);
            }
        });

        jobsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(user_dashboard.this, user_job_search.class);
                startActivity(intent2);
            }
        });

    }
}