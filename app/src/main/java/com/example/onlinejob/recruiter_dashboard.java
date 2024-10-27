package com.example.onlinejob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class recruiter_dashboard extends AppCompatActivity {
ImageView profile,candidate,logout,jobadd;
    Intent intent;
    String username;
    String userpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_dashboard);

        logout=findViewById(R.id.logouticon);
        profile=findViewById(R.id.profileicon);
        candidate=findViewById(R.id.candidateicon);
        jobadd=findViewById(R.id.jobsearch);

        intent=getIntent();

        username=intent.getStringExtra("username");
        userpassword=intent.getStringExtra("userpassword");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(recruiter_dashboard.this, recruiter_login.class);
            startActivity(intent);
            }
        });
 candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(recruiter_dashboard.this, candidate_page.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(recruiter_dashboard.this, recruiter_profile.class);
                intent2.putExtra("recruitername",username);
                intent2.putExtra("recruiterpassword",userpassword);
                startActivity(intent2);
            }
        });

jobadd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent1=new Intent(recruiter_dashboard.this,job_add_page.class);
        startActivity(intent1);
    }
});

    }
}